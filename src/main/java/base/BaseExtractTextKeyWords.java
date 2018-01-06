package base;

import util.bean.KeyWordBean;
import java.util.ArrayList;

/**
 * @author yiding
 */
public abstract class BaseExtractTextKeyWords {

    /**
     * @param content
     * @param topN
     */

    public abstract ArrayList<KeyWordBean> extractKeyWords(String content, int topN) throws Exception;
}
