package mi.bean;

import util.bean.TermBean;

import java.util.LinkedList;

/**
 * @author yidxue
 */
public class WordPairsBean {
    private LinkedList<String> words = new LinkedList<>();
    private float miScore;

    public LinkedList<String> getWords() {
        return words;
    }

    public void addWord(String word) {
        this.words.add(word);
    }

    public float getMiScore() {
        return miScore;
    }

    public void setMiScore(float miScore) {
        this.miScore = miScore;
    }

    public String getWord() {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordPairsBean) {
            WordPairsBean wordPair = (WordPairsBean) obj;
            if (this.getWord().equals(wordPair.getWord())) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.getWord().hashCode();
    }

    @Override
    public String toString() {
        return this.getWord() + ":" + this.miScore;
    }
}
