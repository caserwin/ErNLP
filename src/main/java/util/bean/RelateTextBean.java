package util.bean;

/**
 * @author yiding
 */
public class RelateTextBean {
    private String path;
    private String content;
    private float coef;

    public RelateTextBean(String path, String content, float coef) {
        this.path = path;
        this.content = content;
        this.coef = coef;
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

    public float getCoef() {
        return coef;
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }
}
