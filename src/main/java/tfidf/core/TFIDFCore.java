package tfidf.core;

import base.BaseFileUtil;
import tfidf.bean.TFIDFTextBean;
import tfidf.bean.WordTFIDFBean;
import tfidf.util.TFIDFFileUtil;
import util.SegmentUtil;
import util.bean.TermBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author yiding
 */
public class TFIDFCore {

    private HashMap<String, Integer> wordInDocNum = new HashMap<>();
    private HashSet<String> filePathSet = new HashSet<>();
    private int segType = 1;

    /**
     * 封装文本
     */
    public ArrayList<TFIDFTextBean> getTextBean(ArrayList<String> fileList) throws IOException {
        ArrayList<TFIDFTextBean> textBeanList = new ArrayList<>();
        for (String path : fileList) {
            TFIDFTextBean textBean = new TFIDFTextBean();
            // 每篇文章的词频统计
            HashMap<String, Integer> wordCount = new HashMap<>();
            int wordNumber = 0; // 每篇文章的词语数量
            String content = BaseFileUtil.readFileAllContent(path);
            // 这种分词方式，过滤了停止词，停止词可见：https://github.com/hankcs/HanLP/blob/master/data/dictionary/stopwords.txt
            List<TermBean> termList = SegmentUtil.segment(content, segType);
            for (TermBean term : termList) {
                // 文章词频统计
                if (!wordCount.containsKey(term.getWord())) {
                    wordCount.put(term.getWord(), 1);
                } else {
                    wordCount.put(term.getWord(), wordCount.get(term.getWord()) + 1);
                }
                // 统计文章词语总数
                wordNumber++;
                // 统计该词语在多少篇文章中出现过
                calculateWordInDocNum(term.getWord(), path);
            }

            textBean.setTextPath(path);
            textBean.setWordCount(wordCount);
            textBean.setWordNumber(wordNumber);
            textBeanList.add(textBean);
        }
        return textBeanList;
    }

    /**
     * 保存IDF模型
     */
    public void saveIDFModel(String outPath, ArrayList<String> fileList, int textNumber) throws IOException {
        for (String path : fileList) {
            String content = BaseFileUtil.readFileAllContent(path);
            // 这种分词方式，过滤了停止词，停止词可见：https://github.com/hankcs/HanLP/blob/master/data/dictionary/stopwords.txt
            List<TermBean> termList = SegmentUtil.segment(content, segType);
            for (TermBean term : termList) {
                // 统计该词语在多少篇文章中出现过
                calculateWordInDocNum(term.getWord(), path);
            }
        }
        TFIDFFileUtil.writeFileByLine(outPath, wordInDocNum, textNumber);
    }

    /**
     *
     */
    public void saveTFIDFModel() throws IOException {


    }


    /**
     * 计算一个词语在多少篇文本出现过
     */
    private void calculateWordInDocNum(String word, String filePath) {
        if (!wordInDocNum.containsKey(word)) {
            wordInDocNum.put(word, 1);
            filePathSet.add(filePath);
        } else {
            if (!filePathSet.contains(filePath)) {
                wordInDocNum.put(word, wordInDocNum.get(word) + 1);
            }
        }
    }


    /**
     * 计算每篇文章中每个词语的TF-IDF。
     * 这种数据结构，感觉用于search比较合适
     */
    public HashMap<String, HashSet<WordTFIDFBean>> calculateTFIDFForSearch(ArrayList<TFIDFTextBean> arrTextBeans, int textNumber) {
        HashMap<String, HashSet<WordTFIDFBean>> wordTFIDFMap = new HashMap<>();
        for (TFIDFTextBean textBean : arrTextBeans) {
            HashMap<String, Integer> wordCount = textBean.getWordCount();
            for (Entry entry : wordCount.entrySet()) {
                WordTFIDFBean wordTFIDF = new WordTFIDFBean();
                float tf = (float) entry.getValue() / (float) textBean.getWordNumber();
                float idf = (float) Math.log(textNumber / (float) wordInDocNum.get(entry.getKey().toString()));
                String word = entry.getKey().toString();

                wordTFIDF.setTf(tf);
                wordTFIDF.setIdf(idf);
                wordTFIDF.setTFIDF(tf * idf);
                wordTFIDF.setPath(textBean.getTextPath());

                if (!wordTFIDFMap.containsKey(word)) {
                    HashSet<WordTFIDFBean> set = new HashSet<>();
                    set.add(wordTFIDF);
                    wordTFIDFMap.put(word, set);
                } else {
                    wordTFIDFMap.get(word).add(wordTFIDF);
                }
            }
        }
        return wordTFIDFMap;
    }


    /**
     * 计算每篇文章中每个词语的TF-IDF。
     * 这种数据结构，感觉用于提取文章关键词比较合适。
     */
    public HashMap<String, HashSet<WordTFIDFBean>> calculateTFIDFForExtractKeyWord(ArrayList<TFIDFTextBean> arrTextBeans, int textNumber) {
        HashMap<String, HashSet<WordTFIDFBean>> pathTFIDF = new HashMap<>();
        for (TFIDFTextBean textBean : arrTextBeans) {
            HashMap<String, Integer> wordCount = textBean.getWordCount();
            HashSet<WordTFIDFBean> wordSet = new HashSet<>();
            for (Entry entry : wordCount.entrySet()) {
                WordTFIDFBean wordTFIDF = new WordTFIDFBean();
                float tf = (float) entry.getValue() / (float) textBean.getWordNumber();
                float idf = (float) Math.log(textNumber / (float) wordInDocNum.get(entry.getKey().toString()));
                String word = entry.getKey().toString();

                wordTFIDF.setTf(tf);
                wordTFIDF.setIdf(idf);
                wordTFIDF.setTFIDF(tf * idf);
                wordTFIDF.setWord(word);

                wordSet.add(wordTFIDF);
            }
            pathTFIDF.put(textBean.getTextPath(), wordSet);
        }
        return pathTFIDF;
    }


    public static void main(String[] args) throws Exception {
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


//         对文本进行分词
//        ArrayList<TextBean> arrTextBeans = tf.getTextBean(allTextPathList);

    }
}
