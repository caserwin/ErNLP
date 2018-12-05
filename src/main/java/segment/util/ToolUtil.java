package segment.util;

/**
 * Created by yidxue on 2018/12/5
 */
public class ToolUtil {
    public static void printMatrix(double[][] matrix) {
        for (double[] aMatrix : matrix) {
            for (double anAMatrix : aMatrix) {
                System.out.print(anAMatrix + " ");
            }
            System.out.println();
        }
    }
}