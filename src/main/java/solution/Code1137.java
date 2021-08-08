package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/8 0:13
 */
public class Code1137 {

    public int tribonacci(int n) {
        if (n < 3) {
            if (n == 0) {
                return 0;
            } else {
                return 1;
            }
        }

        int p, q = 0, r = 1, t = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = t;
            t = p + q + r;
        }

        return r;
    }

    public static void main(String[] args) {
        Code1137 code1137 = new Code1137();
        code1137.tribonacci(4);
    }
}
