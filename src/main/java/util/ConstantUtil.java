package util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * to save static constant
 */
public class ConstantUtil {
    private static final Config conf = ConfigFactory.parseResources("common.conf");


    public static final String TFIDF_CORPUS = conf.getString("nlp.corpus");
    public static final String IDF_MODEL = conf.getString("nlp.model.idf");

    public static final String CHI_CORPUS = conf.getString("nlp.corpus");
    public static final String CHI_MODEL = conf.getString("nlp.model.chi");

}

