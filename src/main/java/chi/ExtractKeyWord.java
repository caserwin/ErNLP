package chi;

import base.BaseFileUtil;
import chi.bean.CHITextBean;
import chi.core.CHICore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author yiding
 */
public class ExtractKeyWord {

    public static void main(String[] args) throws IOException {
        // 获得所有文件路径
        ArrayList<String> allTextPathList = new ArrayList<>();
        BaseFileUtil.getAllPath("nlp/SogouC/Reduced/互联网", allTextPathList);

        // 训练
        CHICore chiCore = new CHICore();
        ArrayList<CHITextBean> textBeanList = chiCore.getTextBean(allTextPathList);

        // 获得全部语料库中的词语
        HashSet<String> dicWordSet = chiCore.getDicWordSet();
        System.out.println(dicWordSet.size());




    }


}
