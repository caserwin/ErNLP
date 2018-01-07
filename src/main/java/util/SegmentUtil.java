package util;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import util.bean.TermBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiding
 */
public class SegmentUtil {

    private static List<TermBean> segmentByHanlp(String content) {
        List<TermBean> termBeanLS = new ArrayList<>();
        List<Term> termLS = NotionalTokenizer.segment(content);

        for (Term term : termLS) {
            TermBean termBean = new TermBean();
            termBean.setWord(term.word);
            termBean.setNature(term.nature.toString());
            termBeanLS.add(termBean);
        }
        return termBeanLS;
    }

    public static List<TermBean> segment(String content, int type) {
        switch (type) {
            case 1:
                return segmentByHanlp(content);
            default:
                return segmentByHanlp(content);
        }
    }
}
