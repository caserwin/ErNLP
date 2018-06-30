package util.bean;

/**
 * @author yiding
 */
public class RelateDocBean extends DocBaseBean {
    private float weight;

    public RelateDocBean(String path, String content, float weight) {
        super(path, content);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.getPath() + "\t" + this.weight + "\n" + this.getContent();
    }


    public static void main(String[] args) {
        RelateDocBean relateDocBean = new RelateDocBean("path1", "1233445555", 0.5f);
        System.out.println(relateDocBean.getPath() + "\t" + relateDocBean.getContent() + "\t" + relateDocBean.getWeight());
    }
}
