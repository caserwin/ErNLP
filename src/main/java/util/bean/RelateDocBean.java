package util.bean;

/**
 * @author yiding
 */
public class RelateDocBean {
    private String path;
    private String content;
    private float weight;

    public RelateDocBean(String path, String content, float weight) {
        this.path = path;
        this.content = content;
        this.weight = weight;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.path + "\t" + this.weight + "\n" + this.content;
    }
}
