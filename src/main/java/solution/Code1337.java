package solution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/1 18:15
 */
public class Code1337 {


    public int[] kWeakestRows(int[][] mat, int k) {
        Queue<int[]> queue = new PriorityQueue<>((i1, i2) -> {
            if (i1[0] == i2[0]) {
                return i1[1] - i2[1];
            } else {
                return i1[0] - i2[0];
            }
        });
        for (int i = 0; i < mat.length; i++) {
            queue.add(new int[]{binarySearch(mat[i]) + 1, i});
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll()[1];
        }
        return ret;
    }

    public int binarySearch(int[] data) {
        int left = 0, right = data.length - 1;

        int mid;
        int pos = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (data[mid] == 1) {
                left = mid + 1;
                pos = mid;
            } else {
                right = mid - 1;
            }
        }

        return pos;
    }

    public static void main(String[] args) {
        int[][] data = new int[][]{{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        Code1337 code1337 = new Code1337();
        System.out.println(Arrays.toString(code1337.kWeakestRows(data, 3)));

    }
}
