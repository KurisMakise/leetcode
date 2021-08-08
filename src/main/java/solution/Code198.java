package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/7 23:29
 */
public class Code198 {

    public int rob(int[] nums) {
        return helper(nums, nums.length - 1);
    }

    private int helper(int[] nums, int index) {
        if (index == 0) {
            return nums[0];
        } else if (index == 1) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(nums[index] + helper(nums, index - 2), helper(nums, index - 1));
    }


    public int rob1(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[length - 1];
    }
}
