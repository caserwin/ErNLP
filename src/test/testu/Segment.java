package testu;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import tfidf.bean.WordTFIDFBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Erwin
 * Date: 17/12/29 下午2:57
 * Description:
 */
public class Segment {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int pressure = 1000000;
        String text = "江西鄱阳湖干枯，中国最大的淡水湖变成大草原";
        List<Term> termList = NotionalTokenizer.segment(text);
//
//        for (Term term : termList) {
//            System.out.println(term.word+"/"+term.nature);
//        }
//
//        double costTime = (System.currentTimeMillis() - start) / (double) 1000;
//        System.out.printf("分词速度：%.2f字每秒", text.length() * pressure / costTime);

        HashMap<String,Integer> wordCount = new HashMap<>();
        wordCount.put("a", 3);

        for (Map.Entry entry: wordCount.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println((int)entry.getValue()/(float)10);
        }
    }
}
