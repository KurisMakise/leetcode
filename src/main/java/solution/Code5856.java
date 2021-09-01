package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/29 23:48
 */
public class Code5856 {
    public int minSessions(int[] tasks, int sessionTime) {
        Arrays.sort(tasks);

        int ans = 0;

        int left = 0, right = tasks.length - 1;

        while (left <= right) {
            int remain = sessionTime;
            while (left <= right && remain >= tasks[right]) {
                remain -= tasks[right--];
            }
            while (left <= right && remain >= tasks[left]) {
                remain -= tasks[left++];
            }
            ++ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        Code5856 code5856 = new Code5856();
        int[] tasks = new int[]{
                1, 5, 7, 10, 3, 8, 4, 2, 6, 2
        };
        System.out.println(code5856.minSessions(tasks, 10   ));
    }
}
