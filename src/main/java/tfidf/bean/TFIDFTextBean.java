package tfidf.bean;

import java.util.HashMap;

/**
 * @author yiding
 */

public class TFIDFTextBean {

    private String textPath = null;
    private HashMap<String, Integer> wordCount = null;
    private int wordNumber = 0;

    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }

    public HashMap<String, Integer> getWordCount() {
        return wordCount;
    }

    public void setWordCount(HashMap<String, Integer> wordCount) {
        this.wordCount = wordCount;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }


    @Override
    public String toString() {
        return this.textPath + "\t\t" + this.wordNumber + "\t\t" + this.wordCount.keySet() + "\t\t" + this.wordCount.values();
    }
}
