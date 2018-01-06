package util.bean;


/**
 * @author yiding
 */
public class TermBean {
    private String word;
    private String nature;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TermBean) {
            TermBean term = (TermBean) obj;
            if (this.nature.equals(term.nature) && this.word.equals(term.word)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return (this.word+this.nature).hashCode();
    }

    @Override
    public String toString() {
        return word + "/" + nature;
    }
}
