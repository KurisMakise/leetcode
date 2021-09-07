package solution;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/29 23:19
 */
public class Code5855 {

    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());
        for (String num : nums) {
            priorityQueue.offer(num);
            if (priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        return priorityQueue.peek();
    }

    public String kthLargestNumber1(String[] nums, int k) {
        Arrays.sort(nums, (o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());

        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        Code5855 code5855 = new Code5855();
        String[] nums = new String[]{
                "4", "3", "7", "10"
        };

        System.out.println(code5855.kthLargestNumber1(nums, 4));
    }

}
