//package mi.core;
//
//import mi.bean.WordPairsBean;
//import util.ConstantUtil;
//import util.SegmentUtil;
//import util.bean.TermBean;
//
//import java.util.*;
//
///**
// * @author yidxue
// */
//public class MICore {
//
//    private static int segType = 2;
//    private static int order = 2;
//    // 一阶共现，词频统计
//    private static HashMap<String, Integer> oneWordCount = new HashMap<>();
//    // 多阶共现，词频统计
//    private static HashMap<WordPairsBean, Integer> multiWordCount = new HashMap<>();
//
//    /**
//     * 按标点符号切分
//     */
//    public static String[] splitSentence(String context) {
//        return context.split(ConstantUtil.SENTENCE_SPLIT_REGEX);
//    }
//
//    /**
//     * 滑动窗口，返回多阶词语
//     */
//    public static ArrayList<WordPairsBean> slidingWindow(List<TermBean> termBeans, int order) {
//        ArrayList<WordPairsBean> wordPairsBeans = new ArrayList<>();
//        // 如果比阶数还小，直接返回
//        if (termBeans.size() < order) {
//            return wordPairsBeans;
//        }
//        // 滑动窗口
//        for (int i = 0; i <= termBeans.size() - order; i++) {
//            WordPairsBean wpb = new WordPairsBean();
//            for (int j = i; j < i + order; j++) {
//                wpb.getWords().add(termBeans.get(j).getWord());
//            }
//            wordPairsBeans.add(wpb);
//        }
//        return wordPairsBeans;
//    }
//
//    public static void getWordCount(String[] splitSen) {
//        for (String sentence : splitSen) {
//            List<TermBean> termBeans = SegmentUtil.segment(sentence, segType);
//            ArrayList<WordPairsBean> wordPairsBeans = slidingWindow(termBeans, order);
//            // 一阶共现词频统计
//            for (TermBean tb : termBeans) {
//                System.out.print(tb.getWord() + "\t");
//                if (!oneWordCount.containsKey(tb.getWord())) {
//                    oneWordCount.put(tb.getWord(), 1);
//                } else {
//                    oneWordCount.put(tb.getWord(), oneWordCount.get(tb.getWord()) + 1);
//                }
//            }
//            System.out.print("|");
//
//            // 多阶共现词频统计
//            for (WordPairsBean wpb : wordPairsBeans) {
//                if (!multiWordCount.containsKey(wpb)) {
//                    multiWordCount.put(wpb, 1);
//                } else {
//                    multiWordCount.put(wpb, multiWordCount.get(wpb) + 1);
//                }
//            }
//        }
//    }
//
//
//    private static double calculateMI() {
//
//
//
//    }
//
//
//    /**
//     * 比如：计算两个词语信息熵
//     * 信息熵越大越好
//     */
//    private static double calculate(int allMultiWordCount, int allOneWordCount, int multiWordCount, int... oneWordCounts) {
//        double numeratorP = (double) multiWordCount / allMultiWordCount;
//
//        double denominatorP = 1;
//        for (int oneWordCount : oneWordCounts) {
//            denominatorP *= (double) oneWordCount / allOneWordCount;
//        }
//
//        return Math.log(numeratorP / denominatorP);
//    }
//
//
//    /**
//     * 基于词语共现来实现。
//     * TODO 先测试2阶共现计算是否正确
//     */
//    private static double calculate(HashSet<Integer>[] multiOccurrence, int numerator) {
//        int denominator = 0;
//        for (int i = 0; i < multiOccurrence.length; i++) {
//            denominator += Math.pow(-1, i) * multiOccurrence[i].stream().reduce((a, b) -> a + b).get();
//        }
//        return (double) numerator / denominator;
//    }
//
//
//    public static void main(String[] args) {
//
//        //
//        String context = "对于每一个节点,从根遍历到他的过程就是一个单词。如果这个节点被标记为红色...就表示这个单词存在，否则不存在。标记为红色是一个单词";
//        //
//        String[] splitSen = splitSentence(context);
//        //
//        getWordCount(splitSen);
//        System.out.println();
//        for (Map.Entry wordAndCount : oneWordCount.entrySet()) {
//            String key = (String) wordAndCount.getKey();
//            Integer value = (Integer) wordAndCount.getValue();
//            System.out.println(key + "-->" + value);
//        }
//        System.out.println("==========================");
//        for (Map.Entry wordAndCount : multiWordCount.entrySet()) {
//            WordPairsBean key = (WordPairsBean) wordAndCount.getKey();
//            Integer value = (Integer) wordAndCount.getValue();
//            System.out.println(key.getWord() + "-->" + value);
//        }
//    }
//}
