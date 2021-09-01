package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/29 22:53
 */
public class Code5854 {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[k - 1] - nums[0];
        for (int i = 1; i <= n - k; ++i) {
            min = Math.min(nums[i + k - 1] - nums[i], min);
        }

        return min;
    }

    public static void main(String[] args) {
        Code5854 code4854 = new Code5854();
        int[] nums = new int[]{
                9, 4, 1, 7
        };
        System.out.println(code4854.minimumDifference(nums, 2));
    }
}
