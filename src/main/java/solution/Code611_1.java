package solution;

import java.util.Arrays;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/4 23:54
 */
public class Code611_1 {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int k = i + 1;
            for (int j = i + 1; j < nums.length - 1; j++) {
                while (k + 1 < nums.length && nums[j] + nums[i] > nums[k + 1]) {
                    k++;
                }
                ans += Math.max(k - j, 0);
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        Code611_1 code611_1 = new Code611_1();
        int[] nums = new int[]{7, 0, 0, 0};
        code611_1.triangleNumber(nums);
    }

}
