package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/6 0:00
 */
public class Code704 {

    public int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }


}
