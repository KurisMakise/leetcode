package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/24 13:38
 */
public class Code787 {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //dp[i}[j]  i目的地 j最多转多少次
        //dp[i][j] = dp[i-1][j-1] +  (i-1,i)cost   dp
        int[][] dp = new int[n][k + 1];
        int fill = 10000 * 101 + 1;
        for (int[] d : dp) {
            Arrays.fill(d, fill);
        }

        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[flight[1]][0] = flight[2];
            }
        }

        //dp[src][]
        for (int i = 1; i <= k; i++) {
            for (int[] flight : flights) {
                dp[flight[1]][i] = Math.min(dp[flight[0]][i - 1] + flight[2], dp[flight[1]][i]);
            }
        }

        int ans = fill;
        for (int j = 0; j <= k; ++j) {
            ans = Math.min(dp[dst][j], ans);
        }

        return ans == fill ? -1 : ans;
    }

    public static void main(String[] args) {
        Code787 code787 = new Code787();
        int[][] flights = new int[][]{
                {4, 1, 1}, {1, 2, 3}, {0, 3, 2}, {0, 4, 10}, {3, 1, 1}, {1, 4, 3}
        };
        int[][] flights1 = new int[][]{
                {0, 1, 100}, {1, 2, 100}, {0, 2, 500}
        };
        System.out.println(code787.findCheapestPrice(3, flights1, 0, 2, 1));
    }
}
