package chi.bean;

import java.util.HashMap;

/**
 * @author yiding
 */
public class CategoryBean {
    private String categoey;
    private HashMap<String, Float> wordChi;

    public String getCategoey() {
        return categoey;
    }

    public void setCategoey(String categoey) {
        this.categoey = categoey;
    }

    public HashMap<String, Float> getWordChi() {
        return wordChi;
    }

    public void setWordChi(HashMap<String, Float> wordChi) {
        this.wordChi = wordChi;
    }
}
