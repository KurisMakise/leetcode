package solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/11 22:15
 */
public class Code446 {

    public int numberOfArithmeticSlices(int[] nums) {
        int length = nums.length;

        Map<Long, Integer>[] f = new Map[length];

        int ans = 0;

        for (int i = 0; i < length; i++) {
            f[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long d = (long) nums[i] - nums[j];
                //获取f[j] 弱等差序列数
                int cnt = f[j].getOrDefault(d, 0);
                ans += cnt;
                //更新f[i][d] 弱等差序列数
                //f[i][d] += f[j][d]+1
                f[i].put(d, f[i].getOrDefault(d, 0) + cnt + 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Code446 code446 = new Code446();
        int[] nums = new int[]{7, 7, 7, 7, 7};
        System.out.println(code446.numberOfArithmeticSlices(nums));
    }
}
