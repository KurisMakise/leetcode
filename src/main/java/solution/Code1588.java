package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/29 10:53
 */
public class Code1588 {

    public int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        int n = arr.length;

        for (int i = 0; i < n; ++i) {
            ans += arr[i];
            int length = 1;
            int tmp = arr[i];
            for (int j = i + 1; j < n; ++j) {
                tmp += arr[j];
                ++length;
                if (length % 2 == 1) {
                    ans += tmp;
                }
            }
        }


        return ans;
    }
}
