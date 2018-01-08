package tfidf.core;

import base.BaseExtractTextKeyWords;
import base.BaseFileUtil;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import tfidf.util.TFIDFFileUtil;
import util.CommonUtil;
import util.ConstantUtil;
import util.bean.KeyWordBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yiding
 */

public class ExtractKeyWordTFIDF extends BaseExtractTextKeyWords {

    // query 的词频统计
    private static HashMap<String, Integer> wordCount = new HashMap<>();

    static {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    }

    /**
     * 提取关键词
     */
    @Override
    public KeyWordBean[] extractKeyWords(String content, int topN) throws Exception {
        // 先分词
        List<Term> termList = NotionalTokenizer.segment(content);
        // 计算TD-IDF
        Map<String, Float> wordTFIDF = calculateTFIDF(termList);
        // 返回 topN 词语。
        return CommonUtil.getTopNWord(wordTFIDF, topN);
    }

    /**
     * 训练
     */
    public static void train() throws IOException {
        TFIDFCore tfc = new TFIDFCore();
        // 获得所有训练文本路径
        ArrayList<String> allTextPathList = new ArrayList<>();
        BaseFileUtil.getAllPath(ConstantUtil.TFIDF_CORPUS, allTextPathList);
        // 文章总数量
        int textNumber = allTextPathList.size();
        // 保存模型, 计算每个词语在多少文章出现。
        tfc.saveIDFModel(ConstantUtil.IDF_MODEL, allTextPathList, textNumber);
    }

    /**
     * 计算每个词语的tf
     */
    private static HashMap<String, Float> calculateTF(List<Term> termList) {
        HashMap<String, Float> wordTF = new HashMap<>();
        // 记录总词语数
        float allWord = (float) termList.size();
        // 先做一遍词频统计
        for (Term term : termList) {
            if (!wordCount.containsKey(term.word)) {
                wordCount.put(term.word, 1);
            } else {
                wordCount.put(term.word, wordCount.get(term.word) + 1);
            }
        }
        // 计算TF
        for (Map.Entry entry : wordCount.entrySet()) {
            wordTF.put(entry.getKey().toString(), Integer.parseInt(entry.getValue().toString()) / allWord);
        }
        return wordTF;
    }

    /**
     * 计算每个词语的IDF值
     */
    private static Map<String, Float> calculateIDF() throws Exception {
        // 用于存放每个词语的idf
        Map<String, Float> wordIDF = new HashMap<>();
        // 获取model
        Map<String, Integer> modelMap = TFIDFFileUtil.getModelForIDF(ConstantUtil.IDF_MODEL);

        // 获取文章总数
        int allText = modelMap.get("text_Number") + 1;
        modelMap.remove("text_Number");
        // 计算每个词的idf
        for (String word : wordCount.keySet()) {
            int textNum = 1;
            if (modelMap.containsKey(word)) {
                textNum += modelMap.get(word);
            }
            float idf = (float) Math.log(allText / (double) textNum);
            wordIDF.put(word, idf);
        }
        return wordIDF;
    }

    /**
     * 计算每个词语的tf-idf值
     */
    private static Map<String, Float> calculateTFIDF(List<Term> termList) throws Exception {
        // 计算每个词语的TF值
        Map<String, Float> wordTF = calculateTF(termList);
        // 计算每个词语的idf值
        Map<String, Float> wordIDF = calculateIDF();
        // 存放每个词语的TF-IDF值
        Map<String, Float> wordTFIDF = new HashMap<>();
        // 计算TF-IDF值
        for (String word : wordCount.keySet()) {
            wordTFIDF.put(word, wordTF.get(word) * wordIDF.get(word));
        }
        return wordTFIDF;
    }
}