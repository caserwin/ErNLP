package cluster.kmeans;

/**
 * Created by yidxue on 2018/4/7
 * @author yidxue
 */
public class DistanceCompute {
    /**
     * 求欧式距离
     */
    public double getEuclideanDis(Point p1, Point p2) {
        double countDis = 0;
        float[] p1LocalArray = p1.getlocalArray();
        float[] p2LocalArray = p2.getlocalArray();

        if (p1LocalArray.length != p2LocalArray.length) {
            throw new IllegalArgumentException("length of array must be equal!");
        }

        for (int i = 0; i < p1LocalArray.length; i++) {
            countDis += Math.pow(p1LocalArray[i] - p2LocalArray[i], 2);
        }

        return Math.sqrt(countDis);
    }
}
