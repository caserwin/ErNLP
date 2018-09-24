package tfidf.demo;

import tfidf.core.ExtractKeyWordTFIDF;
import util.bean.KeyWordBean;

/**
 * @author yiding
 */
public class QueryKeyWordExtractDemo {
    public static void main(String[] args) throws Exception {
        // 训练
        ExtractKeyWordTFIDF.train();
        // 输出
        String content = "今年互联网就业行情不好";
        KeyWordBean[] topNKeyWord = new ExtractKeyWordTFIDF().extractKeyWords(content, 3);
        for (KeyWordBean kw : topNKeyWord) {
            System.out.println(kw.getWord() + "-->" + kw.getWeights());
        }
    }
}
