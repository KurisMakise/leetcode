package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/26 10:16
 */
public class Code59 {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int[][] directions = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };
        int x = 0, y = 0;

        int maxNum = n * n;
        int curNum = 0;

        int directionIndex = 0;
        while (curNum < maxNum) {
            matrix[x][y] = ++curNum;

            int nextX = x + directions[directionIndex][0], nextY = y + directions[directionIndex][1];
            if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > n - 1 || matrix[nextX][nextY] != 0) {
                directionIndex = (directionIndex + 1) % 4;
            }

            x += directions[directionIndex][0];
            y += directions[directionIndex][1];
        }
        return matrix;
    }

    public int[][] generateMatrix1(int n) {
        int[][] matrix = new int[n][n];

        int[] directions = new int[]{
                1, 2, 3, 4
        };
        int x = 0, y = 0;
        int limit = n - 1;
        int direction = directions[0];

        for (int num = 1; num <= n * n; ++num) {
            matrix[x][y] = num;
            switch (direction) {
                case 1:
                    if (y == limit) {
                        direction = 2;
                        ++x;
                    } else {
                        ++y;
                    }
                    break;
                case 2:
                    if (x == limit) {
                        direction = 3;
                        --y;
                    } else {
                        ++x;
                    }
                    break;
                case 3:
                    if (y == n - limit - 1) {
                        direction = 4;
                        --x;
                    } else {
                        --y;
                    }
                    break;
                case 4:
                    if (x == n - limit) {
                        --limit;
                        direction = 1;
                        ++y;
                    } else {
                        --x;
                    }
                    break;
                default:
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        Code59 code59 = new Code59();

        System.out.println(Arrays.deepToString(code59.generateMatrix(3)));
        System.out.println(Arrays.deepToString(code59.generateMatrix(4)));
        System.out.println(Arrays.deepToString(code59.generateMatrix(5)));
        System.out.println(Arrays.deepToString(code59.generateMatrix(6)));
    }

}
