package chi.core;

import base.BaseExtractCateKeyWords;
import base.BaseFileUtil;
import chi.bean.CHITextBean;
import chi.util.CHIFileUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import util.bean.KeyWordBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author yiding
 */
public class ExtractKeyWordCHI extends BaseExtractCateKeyWords {

    private static Config conf = ConfigFactory.parseResources("common.conf");

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
    public static HashMap<String, HashMap<String, Float>> getCateKeyWord() throws IOException {
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

    @Override
    public ArrayList<KeyWordBean> extractKeyWords(int topN) throws Exception {
        return null;
    }

    public static void main(String[] args) throws IOException {
//        train();
        HashMap<String, HashMap<String, Float>> categoryKeyWord = CHIFileUtil.getCHIModel(conf.getString("nlp.model.chi"));
        for (Map.Entry cateKeyWordEntry : categoryKeyWord.entrySet()) {
            System.out.println(cateKeyWordEntry.getKey().toString() +"\t"+ ((HashMap<String, Float>)cateKeyWordEntry.getValue()).size());
        }

    }


}
