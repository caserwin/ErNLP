package base;

import util.bean.KeyWordBean;
import java.util.ArrayList;
import java.util.Map;

/**
 * User: Erwin
 * Date: 18/1/6 下午4:25
 * Description:
 */
public abstract class BaseExtractCateKeyWords {
    /**
     *
     * @param topN
     * 1. classify word by topic.
     * 2. also means extract word by category.
     */
    public abstract Map<String, KeyWordBean[]> extractKeyWords(int topN) throws Exception;
}
