package tfidf.core;

import base.BaseFileUtil;
import tfidf.bean.TFIDFTextBean;
import tfidf.bean.WordTFIDFBean;
import tfidf.util.TFIDFFileUtil;
import util.CommonUtil;
import util.ConstantUtil;
import util.SegmentUtil;
import util.bean.RelateDocBean;
import util.bean.TermBean;

import java.io.IOException;
import java.util.*;

/**
 * @author yiding
 */
public class SearchDocumentTFIDF {

    private int segType = 1;

    public static void train() throws IOException {
        TFIDFCore tfc = new TFIDFCore();
        // 获得所有训练文本路径
        ArrayList<String> allTextPathList = new ArrayList<>();
        BaseFileUtil.getAllPath(ConstantUtil.TFIDF_CORPUS, allTextPathList);
        // 文章总数量
        int textNumber = allTextPathList.size();
        // 封装文本文件
        ArrayList<TFIDFTextBean> textBeanList = tfc.getTextBean(allTextPathList);
        // 保存tf-idf模型
        tfc.saveTFIDFModel(ConstantUtil.TFIDF_MODEL, textBeanList, textNumber);
    }

    public RelateDocBean[] searchRelateDoc(String query, int topN) throws Exception {
        // 先分词
        List<TermBean> termList = SegmentUtil.segment(query, segType);
        // tf-idf 模型
        List<WordTFIDFBean> wordTFIDFModel = TFIDFFileUtil.getTFIDFModel(ConstantUtil.TFIDF_MODEL);
        // 计算每篇文章和 search 的相似度
        return getRelateText(wordTFIDFModel, termList, topN);
    }


    private RelateDocBean[] getRelateText(List<WordTFIDFBean> wordTFIDFModel, List<TermBean> termList, int topN) throws Exception {
        // 生成 word-doc 模型
        HashMap<String, HashSet<WordTFIDFBean>> wordTFIDFMap = new HashMap<>();
        for (WordTFIDFBean wordTFIDF : wordTFIDFModel) {
            if (!wordTFIDFMap.containsKey(wordTFIDF.getWord())) {
                HashSet<WordTFIDFBean> set = new HashSet<>();
                set.add(wordTFIDF);
                wordTFIDFMap.put(wordTFIDF.getWord(), set);
            } else {
                wordTFIDFMap.get(wordTFIDF.getWord()).add(wordTFIDF);
            }
        }
        // 生成 doc-weight 模型
        Map<String, Float> docWeight = new HashMap<>();
        for (TermBean term : termList) {
            HashSet<WordTFIDFBean> documentSet = wordTFIDFMap.get(term.getWord());
            for (WordTFIDFBean wb : documentSet) {
                if (!docWeight.containsKey(wb.getPath())) {
                    docWeight.put(wb.getPath(), wb.getTFIDF());
                } else {
                    docWeight.put(wb.getPath(), wb.getTFIDF() + docWeight.get(wb.getPath()));
                }
            }
        }
        // get topN document
        return CommonUtil.getTopNDoc(docWeight, topN);
    }
}
