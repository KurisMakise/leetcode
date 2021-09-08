package solution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/8 22:27
 */
public class Code502 {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int n = profits.length;
        int[][] tmp = new int[n][2];
        for (int i = 0; i < n; ++i) {
            tmp[i] = new int[]{
                    capital[i], profits[i]
            };
        }

        Arrays.sort(tmp, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int index = 0;
        for (int i = 0; i < k; ++i) {
            while (index < n && w >= tmp[index][0]) {
                pq.add(tmp[index][1]);
                ++index;
            }
            if (pq.isEmpty()) {
                break;
            }
            w += pq.poll();
        }

        return w;
    }

    public static void main(String[] args) {
        Code502 code502 = new Code502();
        int[] a = new int[]{
                1, 2, 3
        };
        int[] b = new int[]{
                1, 1, 2
        };
        //1
        //2
        //[1,2,3]
        //[1,1,2]
        System.out.println(code502.findMaximizedCapital(10, 0, a, b));
    }
}
