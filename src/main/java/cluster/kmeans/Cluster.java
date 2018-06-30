package cluster.kmeans;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yidxue on 2018/4/7
 * @author yidxue
 */
public class Cluster {
    /**
     * 标识
     */
    private int id;
    /**
     * 中心
     */
    private Point center;
    /**
     * 成员
     */
    private List<Point> members = new ArrayList<Point>();

    public Cluster(int id, Point center) {
        this.id = id;
        this.center = center;
    }

    public Cluster(int id, Point center, List<Point> members) {
        this.id = id;
        this.center = center;
        this.members = members;
    }

    public void addPoint(Point newPoint) {
        if (!members.contains(newPoint)){
            members.add(newPoint);
        }else{
            System.out.println("样本数据点 {"+newPoint.toString()+"} 已经存在！");
        }
    }

    public int getId() {
        return id;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public List<Point> getMembers() {
        return members;
    }


    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("Cluster \n" + "Cluster_id=" + this.id + ", center:{" + this.center.toString() + "}");
        for (Point point : members) {
            toString.append("\n").append(point.toString());
        }
        return toString+"\n";
    }
}
