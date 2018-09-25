package util;

import base.BaseFileUtil;
import util.bean.CategoryBean;
import util.bean.KeyWordBean;
import util.bean.RelateDocBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author yiding
 */
public class CommonUtil {

    /**
     * 排序比较器
     */
    public static class ValueComparator implements Comparator<Map.Entry<String, Float>> {
        @Override
        public int compare(Map.Entry<String, Float> m, Map.Entry<String, Float> n) {
            if (n.getValue() >= m.getValue()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * 获得 top-N 关键词
     */
    public static KeyWordBean[] getTopNWord(Map<String, Float> wordWeight, int topN) {
        KeyWordBean[] keyWords = new KeyWordBean[topN];
        List<Map.Entry<String, Float>> list = new ArrayList<>(wordWeight.entrySet());
        CommonUtil.ValueComparator vc = new CommonUtil.ValueComparator();
        list.sort(vc);
        for (int i = 0; i < topN; i++) {
            keyWords[i] = new KeyWordBean(list.get(i).getKey(), list.get(i).getValue());
        }
        return keyWords;
    }

    /**
     * 获得 top-N 相关文档
     */
    public static RelateDocBean[] getTopNDoc(Map<String, Float> wordWeight, int topN) throws IOException {
        RelateDocBean[] relateDoc = new RelateDocBean[topN];
        List<Map.Entry<String, Float>> list = new ArrayList<>(wordWeight.entrySet());
        CommonUtil.ValueComparator vc = new CommonUtil.ValueComparator();
        list.sort(vc);
        for (int i = 0; i < topN; i++) {
            String path = list.get(i).getKey();
            float weight = Float.parseFloat(list.get(i).getValue().toString());
            String content = BaseFileUtil.readFileAllContent(path);
            relateDoc[i] = new RelateDocBean(path, content, weight);
        }
        return relateDoc;
    }

    /**
     * 获得 top-N 类别
     */
    public static CategoryBean[] getTopNCate(Map<String, Float> cateWeight, int topN) throws IOException {
        CategoryBean[] relateCate = new CategoryBean[topN];
        List<Map.Entry<String, Float>> list = new ArrayList<>(cateWeight.entrySet());
        CommonUtil.ValueComparator vc = new CommonUtil.ValueComparator();
        list.sort(vc);
        for (int i = 0; i < topN; i++) {
            String cate = list.get(i).getKey();
            float weight = Float.parseFloat(list.get(i).getValue().toString());
            relateCate[i] = new CategoryBean(cate, weight);
        }
        return relateCate;
    }

    /**
     * 词频统计
     */
    public static void wordCount(){
    }
}
