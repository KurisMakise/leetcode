package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/25 17:09
 */
public class Code75 {
    public void sortColors1(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;

        for (int i = 0; i <= p2; i++) {
            while (i < p2 && nums[p2] == 2) {
                --p2;
            }
            if (nums[i] == 2) {
                swap(nums, i, p2--);
            }
            if (nums[i] == 0) {
                swap(nums, i, p0++);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public void sortColors(int[] nums) {
        int x = 0, y = 0, z = 0;

        for (int n : nums) {
            if (n == 0) {
                ++x;
            } else if (n == 1) {
                ++y;
            } else if (n == 2) {
                ++z;
            }
        }
        int index = 0;
        while (x-- > 0) {
            nums[index++] = 0;
        }
        while (y-- > 0) {
            nums[index++] = 1;
        }
        while (z-- > 0) {
            nums[index++] = 2;
        }
    }

    public static void main(String[] args) {
        Code75 code75 = new Code75();
        int[] nums = new int[]{
                2, 1, 2
        };
        code75.sortColors1(nums);
        System.out.println(Arrays.toString(nums));
    }
}
