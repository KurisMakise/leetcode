package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/23 13:16
 */
public class Code15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();


        for (int i = 0; i < n - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = n - 1;
            for (int j = i + 1; j < n - 1; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                while (nums[i] + nums[j] > -nums[k] && k > j) {
                    --k;
                }
                if (j == k) {
                    break;
                }

                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[j]);
                    res.add(nums[k]);
                    result.add(res);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Code15 code15 = new Code15();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(code15.threeSum(nums));
    }
}
