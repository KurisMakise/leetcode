package solution;

import java.util.Arrays;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/1 20:33
 */
public class Code189 {


    public void rotate(int[] nums, int k) {

        int length = nums.length;
        int[] d = Arrays.copyOf(nums, k);
        for (int i = 0; i < k; i++) {
            int index = i;

            int tmp = d[index];
            while (index < length) {
                int t = nums[(index + k) % length];
                nums[(index + k) % length] = tmp;
                tmp = t;
                index += k;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public void rotate1(int[] nums, int k) {

        int length = nums.length;

        int[] newArr = new int[length];

        for (int i = 0; i < length; i++) {
            newArr[(i + k) % length] = nums[i];
        }

        System.arraycopy(newArr, 0, nums, 0, length);
        System.out.println(Arrays.toString(newArr));

    }

    public void rotate2(int[] nums, int k) {
        if (k > nums.length) {
            k %= nums.length;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

        System.out.println(Arrays.toString(nums));
    }

    private void reverse(int[] nums, int start, int end) {
        if (end >= nums.length) {
            return;
        }
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }


    public static void main(String[] args) {
        Code189 code189 = new Code189();
        //                    [5, 6, 7, 1, 2, 3, 4]
        // 1 2 3 4   3 2 1 4
        //[-1,-100,3,99]
        int[] data = new int[]{1, 2, 3};
        code189.rotate2(data, 4);
    }
}
