package ig;

import util.bean.DocBaseBean;
import java.util.HashSet;

/**
 * Created by yidxue on 2018/2/26
 */

public class IGDocBean extends DocBaseBean {
    private int id;
    private String[] words;

    public IGDocBean() {
        super();
    }

    public IGDocBean(int id, String content, String[] words) {
        super(content);
        this.id = id;
        this.words = words;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getPath() + "\t" + this.id + "\t" + this.words.length;
    }
}
