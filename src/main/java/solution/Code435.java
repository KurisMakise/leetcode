package solution;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/26 15:37
 */
public class Code435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(value -> value[0]));
        int n = intervals.length;

        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            int[] intervalA = intervals[i];
            int[] intervalB = intervals[i + 1];
            if (intervalA[0] == intervalB[0]) {
                if (intervalA[1] < intervalB[1]) {
                    intervals[i + 1] = intervals[i];
                }
                ++ans;
            } else if (intervalA[1] > intervalB[0]) {
                if (intervalA[1] < intervalB[1]) {
                    intervals[i + 1] = intervals[i];
                }
                ++ans;
            }
        }

        return ans;
    }

    public int eraseOverlapIntervals1(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(value -> value[1]));

        int n = intervals.length;

        int ans = 1;
        int right = intervals[0][1];

        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }

        return n - ans;
    }

    public static void main(String[] args) {
        Code435 code435 = new Code435();
        int[][] intervals = new int[][]{
                {1, 2}, {2, 3}, {2, 3}, {1, 3}
        };
        System.out.println(code435.eraseOverlapIntervals(intervals));
        System.out.println(code435.eraseOverlapIntervals1(intervals));
    }
}
