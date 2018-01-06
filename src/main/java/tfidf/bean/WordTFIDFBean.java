package tfidf.bean;


/**
 * @author yiding
 */
public class WordTFIDFBean {

    private String word = null;
    private String path = null;
    private float tf = 0;
    private float idf = 0;
    private float tfidf = 0;

    public float getTFIDF() {
        return tfidf;
    }

    public void setTFIDF(float tfidf) {
        this.tfidf = tfidf;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getTf() {
        return tf;
    }

    public void setTf(float tf) {
        this.tf = tf;
    }

    public float getIdf() {
        return idf;
    }

    public void setIdf(float idf) {
        this.idf = idf;
    }

    /**
     * 判断Term是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordTFIDFBean) {
            WordTFIDFBean word = (WordTFIDFBean) obj;
            if (this.path.equals(word.path) && this.word.equals(word.word)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return (this.path + this.word).hashCode();
    }

}
