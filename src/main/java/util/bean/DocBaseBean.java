package util.bean;

import java.util.HashSet;

/**
 * Created by yidxue on 2018/2/26
 */
public class DocBaseBean {
    /**
     * document 路径
     */
    private String path;
    /**
     * document 内容
     */
    private String content;

    public DocBaseBean() {
    }

    public DocBaseBean(String content) {
        this.content = content;
    }

    public DocBaseBean(String path, String context) {
        this.path = path;
        this.content = context;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String context) {
        this.content = context;
    }
}
