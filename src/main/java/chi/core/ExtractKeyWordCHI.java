package chi.core;

import base.BaseExtractCateKeyWords;
import base.BaseFileUtil;
import chi.bean.CHITextBean;
import chi.util.CHIFileUtil;
import util.CommonUtil;
import util.ConstantUtil;
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
        new CHICore().saveCHIModel(ConstantUtil.CHI_MODEL, categoryKeyWord);
    }

    /**
     * 计算
     */
    private static HashMap<String, HashMap<String, Float>> getCateKeyWord() throws IOException {
        CHICore chiCore = new CHICore();
        String corpusPath = ConstantUtil.CHI_CORPUS;
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
        HashMap<String, HashMap<String, Float>> categoryKeyWord = CHIFileUtil.getCHIModel(ConstantUtil.IDF_MODEL);
        for (Map.Entry cateEntry : categoryKeyWord.entrySet()) {
            String cate = cateEntry.getKey().toString();
            KeyWordBean[] keyWords = CommonUtil.getTopNWord((HashMap<String, Float>) cateEntry.getValue(), topN);
            cateKeyWords.put(cate, keyWords);
        }
        return cateKeyWords;
    }
}
