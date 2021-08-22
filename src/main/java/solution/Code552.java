package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/18 10:43
 */
public class Code552 {


    public int checkRecord(int n) {
        //第i天有j个连续'L' k个'A'的总数
        //dp[i][j][k]
        //'L'迟到  dp[i][j+1] = dp[i][j]
        //'A' 旷课  dp[i][j][k] = dp[i-1][
        //'P'     dp[i][j] = dp[i][j]
        int mod = 1000000007;
        int[][][] dp = new int[n + 1][3][2];

        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            //'L'
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j + 1][k] = (dp[i][j + 1][k] + dp[i - 1][j][k]) % mod;
                }
            }

            //'A'
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 1; k++) {
                    dp[i][0][k + 1] = (dp[i][0][k + 1] + dp[i - 1][j][k]) % mod;
                }
            }
            //L A
            //p
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][0][k] = (dp[i][0][k] + dp[i - 1][j][k]) % mod;
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 2; k++) {
                ans = (ans + dp[n][j][k]) % mod;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code552 code552 = new Code552();
        System.out.println(code552.checkRecord(1));
        System.out.println(code552.checkRecord(2));
        System.out.println(code552.checkRecord(55));
        System.out.println(code552.checkRecord(10101));
    }


}
