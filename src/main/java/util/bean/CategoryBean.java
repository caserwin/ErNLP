package util.bean;

/**
 * @author yidxue
 */
public class CategoryBean {
    /**
     *
     */
    private String category;

    /**
     * 权重
     */
    private float weights;

    public CategoryBean(String category, float weights) {
        this.category = category;
        this.weights = weights;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getWeights() {
        return weights;
    }

    public void setWeights(float weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return this.category + "\t" + this.weights;
    }
}
