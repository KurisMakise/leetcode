package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/11 0:09
 */
public class Code413 {
    public int numberOfArithmeticSlices(int[] nums) {

        int ans = 0, d = nums[1] - nums[0];

        int t = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == d) {
                t++;
                ans += t;
            } else {
                d = nums[i] - nums[i - 1];
                t = 0;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Code413 code413 = new Code413();
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(code413.numberOfArithmeticSlices(nums));
    }

}
