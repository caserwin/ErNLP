package tfidf.core;

import base.BaseFileUtil;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import tfidf.bean.TFIDFTextBean;
import tfidf.bean.WordTFIDFBean;
import util.ConstantUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author yiding
 */
public class SearchDocumentTFIDF {


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






    public void getRelateTextBySearch(String search, int topN) throws IOException {
        // 先分词
        List<Term> termList = NotionalTokenizer.segment(search);
        // 计算每篇文章和 search 的相似度
        calculateText(termList);

    }


    public void calculateText(List<Term> termList) throws IOException {
        TFIDFCore tfc = new TFIDFCore();
        // 获得所有训练文本路径
        ArrayList<String> allTextPathList = new ArrayList<>();
        BaseFileUtil.getAllPath("nlp/SogouC/Reduced/互联网", allTextPathList);
        // 文章总数量
        int textNumber = allTextPathList.size();
        // 封装文本文件
        ArrayList<TFIDFTextBean> textBeanList = tfc.getTextBean(allTextPathList);
        // 计算每个词语的tf-idf
        HashMap<String, HashSet<WordTFIDFBean>> wordTFIDFMap = tfc.calculateTFIDFForSearch(textBeanList, textNumber);

        for (Term term : termList) {
            HashSet<WordTFIDFBean> textPathSet = wordTFIDFMap.get(term.word);


        }


    }

}
