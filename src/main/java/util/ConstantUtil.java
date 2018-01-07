package util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * to save static constant
 */
public class ConstantUtil {
    private static final Config conf = ConfigFactory.parseResources("common.conf");

    // tf-idf使用的语料库路径
    public static final String TFIDF_CORPUS = conf.getString("nlp.corpus");
    // idf模型存放路径
    public static final String IDF_MODEL = conf.getString("nlp.model.idf");
    // tf-idf模型存放路径
    public static final String TFIDF_MODEL = conf.getString("nlp.model.tfidf");

    // chi 使用的语料库路径
    public static final String CHI_CORPUS = conf.getString("nlp.corpus");
    // chi 模型存放路径
    public static final String CHI_MODEL = conf.getString("nlp.model.chi");

}

