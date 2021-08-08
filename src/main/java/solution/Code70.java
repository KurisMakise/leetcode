package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/7 22:55
 */
public class Code70 {

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int p, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }


        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
