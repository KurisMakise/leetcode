package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/3 21:46
 */
public class Code17_14 {

    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left, j = right;

        int compare = nums[left];
        while (i < j) {
            while (i < j && nums[i] < compare) {
                ++i;
            }
            while (i < j && nums[j] > compare) {
                --j;
            }
            if (i < j && nums[i] == nums[j]) {
                ++i;
            } else {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        quickSort(nums, left, i - 1);
        quickSort(nums, j + 1, right);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
