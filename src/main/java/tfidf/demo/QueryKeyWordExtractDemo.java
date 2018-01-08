package tfidf.demo;

import tfidf.core.ExtractKeyWordTFIDF;
import util.bean.KeyWordBean;

/**
 * @author yiding
 */
public class QueryKeyWordExtractDemo {
    public static void main(String[] args) throws Exception {
        // 训练
//         QueryTFIDF.train();
        // 输出
        String content = "互联网的互联网今年行情不好";
        KeyWordBean[] topNKeyWord = new ExtractKeyWordTFIDF().extractKeyWords(content, 3);
        for (KeyWordBean kw : topNKeyWord) {
            System.out.println(kw.getWord() + "-->" + kw.getWeights());
        }
    }
}
