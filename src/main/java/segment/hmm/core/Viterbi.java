package segment.hmm.core;

/**
 * refer: https://github.com/hankcs/Viterbi
 */
public class Viterbi {
    /**
     * @param obs     观测序列
     * @param states  隐状态
     * @param startP 初始概率（隐状态）
     * @param transP 转移概率（隐状态）
     * @param emitP  发射概率 （隐状态表现为显状态的概率）
     */
    public static int[] compute(int[] obs, int[] states, double[] startP, double[][] transP, double[][] emitP) {
        double[][] v = new double[obs.length][states.length];
        int[][] path = new int[states.length][obs.length];

        for (int y : states) {
            v[0][y] = startP[y] * emitP[y][obs[0]];
            path[y][0] = y;
        }

        for (int t = 1; t < obs.length; ++t) {
            int[][] newpath = new int[states.length][obs.length];

            for (int y : states) {
                double prob = -1;
                int state;
                for (int y0 : states) {
                    double nprob = v[t - 1][y0] * transP[y0][y] * emitP[y][obs[t]];
                    if (nprob > prob) {
                        prob = nprob;
                        state = y0;
                        // 记录最大概率
                        v[t][y] = prob;
                        // 记录路径
                        System.arraycopy(path[state], 0, newpath[y], 0, t);
                        newpath[y][t] = y;
                    }
                }
            }

            path = newpath;
        }

        double prob = -1;
        int state = 0;
        for (int y : states) {
            if (v[obs.length - 1][y] > prob) {
                prob = v[obs.length - 1][y];
                state = y;
            }
        }

        return path[state];
    }
}
