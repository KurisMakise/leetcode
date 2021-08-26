package solution;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/25 22:06
 */
public class Code56 {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int n = intervals.length;

        int mergeTimes = 0;
        for (int i = 0; i < n - 1; ++i) {
            int[] intervalA = intervals[i];
            int[] intervalB = intervals[i + 1];
            if (intervalA[1] >= intervalB[0]) {
                intervals[i + 1] = new int[]{intervalA[0], Math.max(intervalA[1], intervalB[1])};
                intervals[i] = null;
                ++mergeTimes;
            }
        }

        int[][] ans = new int[n - mergeTimes][2];
        int index = 0;
        for (int[] interval : intervals) {
            if (interval == null) {
                continue;
            }
            ans[index++] = interval;
        }
        return ans;
    }


    public static void main(String[] args) {
        Code56 code56 = new Code56();
        int[][] intervals = new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };
        System.out.println(Arrays.deepToString(code56.merge(intervals)));
    }
}
