package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/26 12:35
 */
public class Code240 {

    public boolean searchMatrix(int[][] matrix, int target) {

        for (int[] m : matrix) {
            if (binarySearch(m, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] row, int target) {
        int left = 0, right = row.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (row[mid] == target) {
                return true;
            }
            if (row[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int n = matrix[0].length;

        int x = matrix.length - 1, y = 0;
        //做下标
        while (x >= 0 && y < n) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --x;
            } else {
                ++y;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Code240 code240 = new Code240();
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}
        };
        int[][] matrix1 = new int[][]{
                {-1, 3}
        };
        System.out.println(code240.searchMatrix1(matrix1, 3));
        System.out.println(code240.searchMatrix(matrix, 11));
        System.out.println(code240.searchMatrix1(matrix, 11));
    }


}
