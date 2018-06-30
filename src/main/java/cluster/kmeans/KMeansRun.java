package cluster.kmeans;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
/**
 * Created by yidxue on 2018/4/7
 */
public class KMeansRun {
    /**
     * 簇的个数
     */
    private int kNum;
    /**
     * 迭代次数
     */
    private int iterNum = 10;

    /**
     * 单次迭代最大运行次数
     */
    private int iterMaxTimes = 100000;
    /**
     * 单次迭代实际运行次数
     */
    private int iterRunTimes = 0;
    /**
     *  单次迭代终止条件，两次运行中类中心的距离差
     */
    private float disDiff = (float) 0.01;
    /**
     * 用于存放，原始数据集
     */
    private List<float[]> originalData =null;
    /**
     * 用于存放，原始数据集所构建的点集
     */
    private static List<Point> pointList = null;
    private DistanceCompute disC = new DistanceCompute();
    /**
     * 用于记录每个数据点的维度
     */
    private int len = 0;

    public KMeansRun(int k, List<float[]> originalData) {
        this.kNum = k;
        this.originalData = originalData;
        this.len = originalData.get(0).length;
        //检查规范
        check();
        //初始化点集。
        init();
    }

    /**
     * 检查规范
     */
    private void check() {
        if (kNum == 0){
            throw new IllegalArgumentException("k must be the number > 0");
        }
        if (originalData == null){
            throw new IllegalArgumentException("program can't get real data");
        }
    }

    /**
     * 初始化数据集，把数组转化为Point类型。
     */
    private void init() {
        pointList = new ArrayList<Point>();
        for (int i = 0, j = originalData.size(); i < j; i++){
            pointList.add(new Point(i, originalData.get(i)));
        }
    }

    /**
     * 随机选取中心点，构建成中心类。
     */
    private Set<Cluster> chooseCenterCluster() {
        Set<Cluster> clusterSet = new HashSet<>();
        Random random = new Random();
        for (int id = 0; id < kNum; ) {
            Point point = pointList.get(random.nextInt(pointList.size()));
            // 用于标记是否已经选择过该数据。
            boolean flag =true;
            for (Cluster cluster : clusterSet) {
                if (cluster.getCenter().equals(point)) {
                    flag = false;
                }
            }
            // 如果随机选取的点没有被选中过，则生成一个cluster
            if (flag) {
                Cluster cluster =new Cluster(id, point);
                clusterSet.add(cluster);
                id++;
            }
        }
        return clusterSet;
    }

    /**
     * 为每个点分配一个类
     */
    private void cluster(Set<Cluster> clusterSet){
        // 计算每个点到K个中心的距离，并且为每个点标记类别号
        for (Point point : pointList) {
            float minDis = Integer.MAX_VALUE;
            for (Cluster cluster : clusterSet) {
                float tmpDis = (float) Math.min(disC.getEuclideanDis(point, cluster.getCenter()), minDis);
                if (tmpDis != minDis) {
                    minDis = tmpDis;
                    point.setClusterId(cluster.getId());
                    point.setDist(minDis);
                }
            }
        }
        // 新清除原来所有的类中成员。把所有的点，分别加入每个类别
        for (Cluster cluster : clusterSet) {
            cluster.getMembers().clear();
            for (Point point : pointList) {
                if (point.getClusterid()==cluster.getId()) {
                    cluster.addPoint(point);
                }
            }
        }
    }

    /**
     * 计算每个类的中心位置！
     */
    private boolean calculateCenter(Set<Cluster> clusterSet) {
        boolean ifNeedIter = false;
        for (Cluster cluster : clusterSet) {
            List<Point> pointList = cluster.getMembers();
            float[] sumAll =new float[len];
            // 所有点，对应各个维度进行求和
            for (int i = 0; i < len; i++) {
                for (Point aPointList : pointList) {
                    sumAll[i] += aPointList.getlocalArray()[i];
                }
            }
            // 计算平均值
            for (int i = 0; i < sumAll.length; i++) {
                sumAll[i] = (float) sumAll[i]/pointList.size();
            }
            // 计算两个新、旧中心的距离，如果任意一个类中心移动的距离大于dis_diff则继续迭代。
            if(disC.getEuclideanDis(cluster.getCenter(), new Point(sumAll)) > disDiff){
                ifNeedIter = true;
            }
            // 设置新的类中心位置
            cluster.setCenter(new Point(sumAll));
        }
        return ifNeedIter;
    }

    /**
     * 运行 k-means
     */
    public Set<Cluster> run() {
        Set<Cluster> clusterSet= chooseCenterCluster();
        boolean ifNeedIter = true;
        while (ifNeedIter) {
            cluster(clusterSet);
            ifNeedIter = calculateCenter(clusterSet);
            iterRunTimes ++ ;
        }
        return clusterSet;
    }

    /**
     * 返回实际运行次数
     */
    public int getIterTimes() {
        return iterRunTimes;
    }
}
