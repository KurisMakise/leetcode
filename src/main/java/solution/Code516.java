package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/12 9:57
 */
public class Code516 {


    /**
     * dp 最长子串
     * 动态规划，求出 dp[i][j]
     * nums[i]==nums[j]  dp[i][j] = dp[i+1][j-1]+2
     * nums[i]!=nums[j] dp[i][j] = max(dp[i+1][j],dp[i][j-1])
     *
     * @param s 参数
     * @return 最大回文串长度
     */
    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();

        int[][] dp = new int[length][length];

        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][length - 1];
    }


    public static void main(String[] args) {
        Code516 code516 = new Code516();

        System.out.println(code516.longestPalindromeSubseq("abba"));
    }
}
