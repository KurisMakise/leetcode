package algorithm.started;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/29 21:40
 */
public class Code704 {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Code704 code704 = new Code704();
        int[] i = {1, 3};
        System.out.println(code704.search(i, 3));
    }
}
