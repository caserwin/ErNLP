package chi.demo;

import chi.core.ExtractKeyWordCHI;
import util.bean.KeyWordBean;
import java.util.Map;

/**
 * @author yiding
 */
public class ExtractKeyWord {

    public static void main(String[] args) throws Exception {
        // 训练模型
//        ExtractKeyWordCHI.train();

        // 提取关键词
        ExtractKeyWordCHI extractKeyWordCHI = new ExtractKeyWordCHI();
        Map<String, KeyWordBean[]> cateKeys = extractKeyWordCHI.extractKeyWords(5);
        for (Map.Entry cateKeyWord : cateKeys.entrySet()) {
            String cate = cateKeyWord.getKey().toString();
            StringBuilder wordAndWeight = new StringBuilder();
            for (KeyWordBean kw : (KeyWordBean[]) cateKeyWord.getValue()) {
                wordAndWeight.append(kw.toString()).append("\t");
            }
            System.out.println(cate + "\t" +wordAndWeight.toString());
        }
    }
}
