package solution.code1000;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2020/9/15 22:53
 */
public class Code1582 {

    public int numSpecial(int[][] mat) {
        int[] rows = new int[mat.length];
        int[] cols = new int[mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == 1) {
                for (int j = 0; j < cols.length; j++) {
                    if (cols[j] == 1 && mat[i][j] == 1) {
                        result++;
                        break;
                    }
                }
            }

        }
        return result;
    }
}
