package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/7 0:02
 */
public class Code457 {

    public boolean circularArrayLoop(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = next(nums, i);

            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, slow)] > 0) {
                if (slow == fast) {
                    if (slow == next(nums, slow)) {
                        break;
                    }
                    return true;
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }

            slow = i;
            while (nums[slow] * nums[next(nums, slow)] > 0) {
                int tmp = next(nums, slow);
                nums[slow] = 0;
                slow = tmp;
            }

        }
        return false;
    }

    private int next(int[] nums, int i) {
        int n = nums.length;
        return ((nums[i] + i) % n + n) % n;
    }


    public static void main(String[] args) {
        Code457 code457 = new Code457();
        int[] nums = new int[]{2, -1, 1, 2, 2};
        int[] nums1 = new int[]{-1, 2};
        int[] nums2 = new int[]{-2, 1, -1, -2, -2};
        int[] nums3 = new int[]{-1, 2, 1, 2};
        int[] nums4 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, -5};
        int[] nums5 = new int[]{-2, -3, -9};
        System.out.println(code457.circularArrayLoop(nums1));
        System.out.println(code457.circularArrayLoop(nums));
        System.out.println(code457.circularArrayLoop(nums5));
        System.out.println(code457.circularArrayLoop(nums2));
        System.out.println(code457.circularArrayLoop(nums4));
        System.out.println(code457.circularArrayLoop(nums3));
    }
}
