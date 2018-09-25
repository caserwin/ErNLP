package util;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import util.bean.TermBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yiding
 */
public class SegmentUtil {

    private static List<TermBean> notionalTokenizerByHanlp(String content) {
        List<Term> termLS = NotionalTokenizer.segment(content);
        return getTerm(termLS);
    }

    private static List<TermBean> standardTokenizerByHanlp(String content) {
        List<Term> termLS = StandardTokenizer.segment(content);
        return getTerm(termLS);
    }


    public static List<TermBean> segment(String content, int type) {
        switch (type) {
            case 1:
                return notionalTokenizerByHanlp(content);
            default:
                return standardTokenizerByHanlp(content);
        }
    }

    private static List<TermBean> getTerm(List<Term> termLS){
        List<TermBean> termBeanLS = new ArrayList<>();
        for (Term term : termLS) {
            TermBean termBean = new TermBean();
            termBean.setWord(term.word);
            termBean.setNature(term.nature.toString());
            termBeanLS.add(termBean);
        }
        return termBeanLS;
    }

    public static void main(String[] args){
        String text = "江西鄱阳湖干枯，中国最大的淡水湖变成大草原";
        List<TermBean> lsterm =  standardTokenizerByHanlp(text);
        for (TermBean termBean:lsterm) {
            System.out.println(termBean.toString());
        }
    }
}
