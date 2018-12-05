package segment.hmm.core;

import java.util.*;
import java.text.DecimalFormat;
import segment.bean.CharBean;

/**
 * Created by yidxue on 2018/12/4
 */
public class HMMCore {
    private ArrayList<String> corpus;
    private double[] startProbability = new double[4];
    private double[][] transititonProbability = new double[4][4];
    private double[][] emissionProbability = null;
    private static HashMap<String, Integer> stateEncode = new HashMap<>();
    private HashMap<String, CharBean> charEncode = new HashMap<>();
    private DecimalFormat df = new DecimalFormat("0.0000");

    static {
        stateEncode.put("S", 0);
        stateEncode.put("B", 1);
        stateEncode.put("M", 2);
        stateEncode.put("E", 3);
    }

    public HMMCore(ArrayList<String> corpus) {
        this.corpus = corpus;
    }

    public void train() {
        for (String sentence : this.corpus) {
            String[] wordAndTags = sentence.split("\\s+");
            String lastTag = "";
            int index = 0;
            for (int i = 0; i < wordAndTags.length; i++) {
                String[] tmp = wordAndTags[i].split("/");
                String word = tmp[0];
                String tag = tmp[1];

                if (!charEncode.containsKey(word)) {
                    HashMap<String, Integer> stateNumber = new HashMap<>();
                    stateNumber.put(tag, 1);

                    CharBean charBean = new CharBean(index, word, stateNumber);
                    charEncode.put(word, charBean);
                    index++;
                } else {
                    HashMap<String, Integer> stateNumber = charEncode.get(word).getStateNumber();
                    if (stateNumber.containsKey(tag)) {
                        stateNumber.put(tag, stateNumber.get(tag) + 1);
                    } else {
                        stateNumber.put(tag, 1);
                    }
                    charEncode.get(word).setStateNumber(stateNumber);
                }

                if (i > 0) {
                    transititonProbability[stateEncode.get(lastTag)][stateEncode.get(tag)] += 1;
                }
                startProbability[stateEncode.get(tag)] += 1;
                lastTag = tag;
            }
        }

        buildEmissionMatrix();
        unifyArray(startProbability);
        unifyMatrix(transititonProbability);
        unifyMatrix(emissionProbability);
    }

    private void buildEmissionMatrix() {
        emissionProbability = new double[4][charEncode.size()];
        for (Map.Entry en : charEncode.entrySet()) {
            CharBean charBean = (CharBean) en.getValue();
            for (Map.Entry stateNumber : charBean.getStateNumber().entrySet()) {
                emissionProbability[stateEncode.get(stateNumber.getKey().toString())][charBean.getId()] = (int) stateNumber.getValue();
            }
        }
    }

    private void unifyArray(double[] array) {
        int sum = (int) Arrays.stream(array).sum();
        for (int i = 0; i < array.length; i++) {
            array[i] = Double.parseDouble(df.format(array[i] / sum));
        }
    }

    private void unifyMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int sum = (int) Arrays.stream(matrix[i]).sum();
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Double.parseDouble(df.format(matrix[i][j] / sum));
            }
        }
    }

    /**
     * 返回状态发射矩阵
     */
    public double[][] getEmissionMatrix() {
        return this.emissionProbability;
    }

    /**
     * 返回状态转移矩阵
     */
    public double[][] getStateMatrix() {
        return this.transititonProbability;
    }

    /**
     * 返回初始状态数组
     */
    public double[] getStartProbArray() {
        return this.startProbability;
    }

    /**
     *
     */
    public HashMap<Integer, String> getStateMap() {
        HashMap<Integer, String> idStateMap = new HashMap<>();
        for (Map.Entry en : stateEncode.entrySet()) {
            idStateMap.put((int) en.getValue(), en.getKey().toString());
        }
        return idStateMap;
    }

    /**
     *
     */
    public HashMap<String, Integer> getWordIDMap() {
        HashMap<String, Integer> wordIDMap = new HashMap<>();
        for (Map.Entry en : charEncode.entrySet()) {
            wordIDMap.put(en.getKey().toString(), ((CharBean) en.getValue()).getId());
        }
        return wordIDMap;
    }
}
