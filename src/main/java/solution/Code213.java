package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/8 0:09
 */
public class Code213 {

    public int rob1(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        int[] dp1 = new int[nums.length];
        dp1[0] = 0;
        dp1[1] = nums[1];
        dp1[2] = Math.max(nums[1], nums[2]);
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        dp[2] = Math.max(nums[2] + dp[0], dp[1]);

        for (int i = 3; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
            dp1[i] = Math.max(dp1[i - 1], nums[i] + dp1[i - 2]);
        }

        return Math.max(dp1[length - 1], dp[length - 2]);
    }

    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(robRange(nums, 0, length - 1), robRange(nums, 1, length));
    }

    public int robRange(int[] nums, int start, int end) {

        int first = Math.max(nums[start], nums[start + 1]);
        int second = nums[start];

        for (int i = start + 2; i < end; i++) {
            int tmp = first;
            first = Math.max(first, nums[i] + second);
            second = tmp;
        }

        return first;
    }

    public static void main(String[] args) {
        Code213 code213 = new Code213();
        int[] nums = new int[]{1, 2, 1, 1};
        System.out.println(code213.rob(nums));
    }

}
