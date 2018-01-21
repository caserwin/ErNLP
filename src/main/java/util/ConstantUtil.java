package util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Arrays;
import java.util.HashSet;

/**
 * to save static constant
 * @author yidxue
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


    /**
     * LDA 语料库路径
     */
    public static final String LDA_CORPUS = conf.getString("nlp.lda.corpus");


    /**
     * 标点符号写法改成枚举类？？？会好些吗？
     */

    public static final String SENTENCE_SPLIT_REGEX = "[，。！？；：,.!?,:]";

}

