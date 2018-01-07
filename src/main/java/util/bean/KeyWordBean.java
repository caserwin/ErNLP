package util.bean;

/**
 * @author yiding
 */
public class KeyWordBean {
    /**
     * 词语
     */
    private String word;

    /**
     * 权重
     */
    private float weights;

    public KeyWordBean(String word, float weights) {
        this.word = word;
        this.weights = weights;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public float getWeights() {
        return weights;
    }

    public void setWeights(float weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return this.word + ":" + this.weights;
    }
}
