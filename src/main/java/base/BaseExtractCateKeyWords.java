package base;

import util.bean.KeyWordBean;
import java.util.ArrayList;

/**
 * User: Erwin
 * Date: 18/1/6 下午4:25
 * Description:
 */
public abstract class BaseExtractCateKeyWords {
    /**
     *
     * @param topN
     */
    public abstract ArrayList<KeyWordBean> extractKeyWords(int topN) throws Exception;
}
