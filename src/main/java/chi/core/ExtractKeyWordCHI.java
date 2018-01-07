package chi.core;

import base.BaseExtractCateKeyWords;
import base.BaseFileUtil;
import chi.bean.CHITextBean;
import chi.util.CHIFileUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import util.CommonUtil;
import util.bean.KeyWordBean;
import java.io.IOException;
import java.util.*;

/**
 * @author yiding
 */
public class ExtractKeyWordCHI extends BaseExtractCateKeyWords {

    private static Config conf = ConfigFactory.parseResources("common.conf");
    static {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    }
    /**
     * 训练
     */
    public static void train() throws IOException {
        // 计算每个词语的cate-word 的chi
        HashMap<String, HashMap<String, Float>> categoryKeyWord = getCateKeyWord();
        // 保存模型, 计算每个词语在多少文章出现。
        new CHICore().saveCHIModel(conf.getString("nlp.model.chi"), categoryKeyWord);
    }

    /**
     * 计算
     */
    private static HashMap<String, HashMap<String, Float>> getCateKeyWord() throws IOException {
        CHICore chiCore = new CHICore();
        String corpusPath = conf.getString("nlp.corpus");
        // 获得所有训练文本路径
        ArrayList<String> allTextPathList = new ArrayList<>();
        BaseFileUtil.getAllPath(corpusPath, allTextPathList);
        // 封装文档
        ArrayList<CHITextBean> chiList = chiCore.getTextBean(allTextPathList);
        // 获得所有词语
        HashSet<String> dicWordSet = chiCore.getDicWordSet();
        // 计算每个类别中每个词语的chi, 并返回
        return chiCore.calculateCateWordCHI(chiList, dicWordSet, corpusPath);
    }

    /**
     * get topN for every category
     */
    @Override
    public Map<String, KeyWordBean[]> extractKeyWords(int topN) throws Exception {
        Map<String, KeyWordBean[]> cateKeyWords = new HashMap<>();
        HashMap<String, HashMap<String, Float>> categoryKeyWord = CHIFileUtil.getCHIModel(conf.getString("nlp.model.chi"));
        for (Map.Entry cateEntry : categoryKeyWord.entrySet()) {
            String cate = cateEntry.getKey().toString();
            KeyWordBean[] keyWords = CommonUtil.getTopN((HashMap<String, Float>) cateEntry.getValue(), topN);
            cateKeyWords.put(cate, keyWords);
        }
        return cateKeyWords;
    }
}
