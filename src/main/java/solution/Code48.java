package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/25 22:31
 */
public class Code48 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        //水平翻转
        for (int i = 0; i < n / 2; i++) {
            int[] tmp = matrix[i];
            matrix[i] = matrix[n - 1 - i];
            matrix[n - 1 - i] = tmp;
        }
        //垂直翻转
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}

