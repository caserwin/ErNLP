package segment.bean;

import java.util.HashMap;

/**
 * Created by yidxue on 2018/12/4
 */
public class CharBean {

    /**
     * 每个字符编号
     */
    private int id;

    /**
     * 字符
     */
    private String schar;

    /**
     * 所属的转态
     */
    private HashMap<String, Integer> stateNumber;

    public CharBean(int id, String schar, HashMap<String, Integer> stateNumber) {
        this.id = id;
        this.schar = schar;
        this.stateNumber = stateNumber;
    }

    public HashMap<String, Integer> getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(HashMap<String, Integer> stateNumber) {
        this.stateNumber = stateNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchar() {
        return schar;
    }

    public void setSchar(String schar) {
        this.schar = schar;
    }
}
