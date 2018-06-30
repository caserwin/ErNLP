package cluster.kmeans;

/**
 * Created by yidxue on 2018/4/7
 * @author yidxue
 */
public class Point {
    private float[] localArray;
    private int id;
    /**
     * 标识属于哪个类中心
     */
    private int clusterId;
    /**
     * 标识和所属类中心的距离。
     */
    private float dist;

    public Point(int id, float[] localArray) {
        this.id = id;
        this.localArray = localArray;
    }

    public Point(float[] localArray) {
        //表示不属于任意一个类
        this.id = -1;
        this.localArray = localArray;
    }

    public float[] getlocalArray() {
        return localArray;
    }

    public int getId() {
        return id;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public int getClusterid() {
        return clusterId;
    }

    public float getDist() {
        return dist;
    }

    public void setDist(float dist) {
        this.dist = dist;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Point_id=" + id + "  [");
        for (float aLocalArray : localArray) {
            result.append(aLocalArray).append(" ");
        }
        return result.toString().trim()+"] clusterId: "+clusterId+" dist: "+dist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Point point = (Point) obj;
        if (point.localArray.length != localArray.length) {
            return false;
        }

        for (int i = 0; i < localArray.length; i++) {
            if (Float.compare(point.localArray[i], localArray[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        float x = localArray[0];
        float y = localArray[localArray.length - 1];
        long temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        int result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
