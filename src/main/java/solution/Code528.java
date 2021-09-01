package solution;

import java.util.Arrays;
import java.util.Random;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/30 13:48
 */
public class Code528 {

    private final int[] pre;
    private final int total;

    public Code528(int[] w) {
        int n = w.length;
        pre = new int[n];
        pre[0] = w[0];
        for (int i = 1; i < n; ++i) {
            pre[i] = w[i] + pre[i - 1];
        }
        //3 1 2 1
        //0,2 3,3 4,5 6,6
        total = Arrays.stream(w).sum();
    }

    Random random = new Random();

    public int pickIndex() {
        int target = random.nextInt(total) + 1;
        return binarySearch(target);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{
                3, 14, 1, 7
        };
        Code528 code528 = new Code528(nums);
        System.out.println(code528.pickIndex());
        System.out.println(code528.pickIndex());
        System.out.println(code528.pickIndex());

    }

    private int binarySearch(int target) {
        int left = 0, right = pre.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (pre[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
