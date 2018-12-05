package segment.hmm.core;

import segment.util.ToolUtil;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yidxue on 2018/12/5
 */
public class HMMSegment {
    private HMMCore hmmCore = null;

    public void train(ArrayList<String> corpus) {
        hmmCore = new HMMCore(corpus);
        hmmCore.train();
    }


    public String segment(String sentences) {
//        if (hmmCore == null) {
//            // load data from files
//        }

        double[][] stateMatrix = hmmCore.getStateMatrix();
        double[][] emissionMatrix = hmmCore.getEmissionMatrix();
        double[] startArray = hmmCore.getStartProbArray();

//        ToolUtil.printMatrix(stateMatrix);
//        ToolUtil.printMatrix(emissionMatrix);
        HashMap<Integer, String> idStateMap = hmmCore.getStateMap();
        HashMap<String, Integer> wordIDMap = hmmCore.getWordIDMap();
        int[] observations = getWordIndex(sentences.toCharArray(), wordIDMap);
        int[] result = Viterbi.compute(observations, new int[]{0, 1, 2, 3}, startArray, stateMatrix, emissionMatrix);

        return segment(sentences.toCharArray(), result, idStateMap);
    }

    private static int[] getWordIndex(char[] words, HashMap<String, Integer> wordIDMap) {
        int[] res = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            res[i] = wordIDMap.get(String.valueOf(words[i]));
        }
        return res;
    }

    private String segment(char[] words, int[] result, HashMap<Integer, String> idStateMap) {
        StringBuilder sentence = new StringBuilder();

        for (int i = 0; i < result.length; i++) {
            if ("S".equals(idStateMap.get(result[i])) || "E".equals(idStateMap.get(result[i]))) {
                sentence.append(words[i]).append("  ");
            } else {
                sentence.append(words[i]);
            }
        }
        return sentence.toString().trim();
    }
}
