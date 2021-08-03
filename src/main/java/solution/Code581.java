package solution;

import java.util.Arrays;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/3 23:37
 */
public class Code581 {

    public int findUnsortedSubarray(int[] nums) {
        int[] newArrays = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newArrays);

        int leftLength = 0, rightLength = 0;

        for (int i = 0; i < nums.length; i++) {
            if (newArrays[i] != nums[i]) {
                break;
            }
            leftLength++;
        }

        for (int i = nums.length - 1; i > 0 && i > leftLength - 1; i--) {
            if (newArrays[i] != nums[i]) {
                break;
            }
            rightLength++;
        }

        return nums.length - leftLength - rightLength;
    }

    public static void main(String[] args) {
        Code581 code581 = new Code581();
        int[] nums = new int[]{1, 2, 3, 5, 4};
        System.out.println(code581.findUnsortedSubarray(nums));
    }

}
