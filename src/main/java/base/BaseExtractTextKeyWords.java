package base;

import util.bean.KeyWordBean;

/**
 * @author yiding
 */
public abstract class BaseExtractTextKeyWords {

    /**
     * @param content
     * @param topN
     */

    public abstract KeyWordBean[] extractKeyWords(String content, int topN) throws Exception;
}
