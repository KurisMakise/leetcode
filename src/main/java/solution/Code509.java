package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/7 22:43
 */
public class Code509 {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int fib1(int n) {

        int p, q = 0, r = 1;
        for (int i = 2; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }

        return r;
    }
}
