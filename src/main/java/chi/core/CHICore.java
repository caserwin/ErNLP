package chi.core;

import base.BaseFileUtil;
import chi.bean.CHITextBean;
import chi.util.CHIFileUtil;
import util.SegmentUtil;
import util.bean.TermBean;
import java.io.IOException;
import java.util.*;

/**
 * @author yiding
 */
public class CHICore {

    private HashSet<String> dicWordSet = new HashSet<>();
    private int segType = 1;

    public HashMap<String, HashMap<String, Float>> calculateCateWordCHI(ArrayList<CHITextBean> textBeanList, HashSet<String> dicWordSet, String corpusPath) {
        long start = System.currentTimeMillis();
        // 获得类别
        Set<String> categorys = CHIFileUtil.getCategory(corpusPath);
        // 存放结果
        HashMap<String, HashMap<String, Float>> categoryKeyWord = new HashMap<>();
        for (String cate : categorys) {
            HashMap<String, Float> wordCHI = new HashMap<>();
            for (String word : dicWordSet) {
                int[] resValues = calculateResValue(cate, word, textBeanList);
                float wordForCateCHI = calculateCHI(resValues[0], resValues[1], resValues[2], resValues[3]);
                wordCHI.put(word, wordForCateCHI);
            }
            System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
            System.out.println("================ " + cate + " is done ================");
            categoryKeyWord.put(cate, wordCHI);
        }
        System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
        return categoryKeyWord;
    }


    public void saveCHIModel(String outPath, HashMap<String, HashMap<String, Float>> categoryKeyWord) {
        try {
            CHIFileUtil.writeFileByLine(outPath, categoryKeyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] calculateResValue(String cate, String word, ArrayList<CHITextBean> textBeanList) {
        int[] resValues = new int[4];

        for (CHITextBean chiTextBean : textBeanList) {
            Set<String> wordSet = chiTextBean.getWordset();
            String textCate = chiTextBean.getCategory();
            if (wordSet.contains(word) && cate.equals(textCate)) {
                resValues[0]++;
            }
            if (wordSet.contains(word) && (!cate.equals(textCate))) {
                resValues[1]++;
            }
            if ((!wordSet.contains(word)) && cate.equals(textCate)) {
                resValues[2]++;
            }
            if ((!wordSet.contains(word)) && (!cate.equals(textCate))) {
                resValues[3]++;
            }
        }
        return resValues;
    }


    public ArrayList<CHITextBean> getTextBean(ArrayList<String> fileList) throws IOException {
        ArrayList<CHITextBean> chiList = new ArrayList<>();
        for (String path : fileList) {
            CHITextBean chiTextBean = new CHITextBean();
            HashSet<String> wordSet = new HashSet<>();
            String content = BaseFileUtil.readFileAllContent(path);
            // 这种分词方式，过滤了停止词，停止词可见：https://github.com/hankcs/HanLP/blob/master/data/dictionary/stopwords.txt
            List<TermBean> termList = SegmentUtil.segment(content, segType);
            for (TermBean term : termList) {
                wordSet.add(term.getWord());
                dicWordSet.add(term.getWord());
            }
            String[] paths = path.split("/");
            String category = paths[paths.length - 2];

            chiTextBean.setCategory(category);
            chiTextBean.setPath(path);
            chiTextBean.setWordset(wordSet);
            chiList.add(chiTextBean);
        }
        return chiList;
    }

    public HashSet<String> getDicWordSet() {
        return dicWordSet;
    }

    private float calculateCHI(long a, long b, long c, long d) {
        long n = a + b + c + d;
        return (float) (n * Math.pow(a * d - b * c, 2) / ((a + c) * (a + b) * (b + d) * (c + d)));
    }
}
