package chi.bean;

import java.util.HashSet;

/**
 * @author yiding
 */
public class CHITextBean {

    private String path;
    private HashSet<String> wordset;
    private String category;

    public String getPath() {
        return path;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashSet<String> getWordset() {
        return wordset;
    }

    public void setWordset(HashSet<String> wordset) {
        this.wordset = wordset;
    }


    @Override
    public String toString(){
        return path+"->"+category+"->"+wordset;
    }
}

