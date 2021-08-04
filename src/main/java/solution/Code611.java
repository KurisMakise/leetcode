package solution;

import java.util.Arrays;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/4 23:54
 */
public class Code611 {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = j, right = nums.length - 1;
                int k = j;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (nums[i] + nums[j] > nums[mid]) {
                        left = mid + 1;
                        k = mid;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += k - j;
            }
        }
        return ans;
    }


}
