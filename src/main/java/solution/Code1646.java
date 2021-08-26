package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/23 9:31
 */
public class Code1646 {

    public int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        //当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
        //当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
        int max = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i / 2] + dp[i / 2 + 1];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        Code1646 code1646 = new Code1646();
        System.out.println(code1646.getMaximumGenerated(1));
    }
}
