package solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/16 17:44
 */
public class Code51 {


    public static void main(String[] args) {
        Code51 code51 = new Code51();
        System.out.println(code51.solveNQueens(4));
    }

    private List<List<String>> result = new ArrayList<>();
    private int[] record;

    public List<List<String>> solveNQueens(int n) {
        record = new int[n];
        calQueue(0);
        return result;
    }

    public void calQueue(int curRow) {
        int n = record.length;
        if (curRow == n) {
            addResult(record);
            return;
        }

        for (int column = 0; column < n; column++) {
            if (isOk(curRow, column)) {
                calQueue(curRow + 1);
            }
        }
    }

    public boolean isOk(int row, int column) {
        int leftUp = column - 1, rightUp = column + 1;

        for (int i = row - 1; i >= 0; --i) {
            if (record[i] == column) {
                return false;
            }
            if (leftUp >= 0 && record[i] == leftUp) {
                return false;
            }
            if (rightUp < record.length && record[i] == rightUp) {
                return false;
            }
            --leftUp;
            ++rightUp;
        }
        record[row] = column;
        return true;
    }


    public void addResult(int[] record) {
        List<String> list = new ArrayList<>();

        for (int k : record) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < record.length; j++) {
                if (j == k) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            list.add(sb.toString());
        }
        result.add(list);
    }
}
