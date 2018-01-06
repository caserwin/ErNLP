package util;

import java.util.Comparator;
import java.util.Map;

/**
 * @author yiding
 */
public class CommonUtil {

    /**
     *  排序比较器
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
}
