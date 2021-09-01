package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/28 23:39
 */
public class Code1480 {

    public int[] runningSum(int[] nums) {
        int[] res = new int[nums.length];
        int n = nums.length;
        res[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] + nums[i];
        }
        return res;
    }
}
