package util;

import util.bean.KeyWordBean;

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
    public static KeyWordBean[] getTopN(Map<String, Float> wordWeight, int topN) {
        KeyWordBean[] keyWords = new KeyWordBean[topN];
        List<Map.Entry<String, Float>> list = new ArrayList<>(wordWeight.entrySet());
        CommonUtil.ValueComparator vc = new CommonUtil.ValueComparator();
        list.sort(vc);
        for (int i = 0; i < topN; i++) {
            keyWords[i]= new KeyWordBean(list.get(i).getKey(), list.get(i).getValue());
        }
        return keyWords;
    }
}
