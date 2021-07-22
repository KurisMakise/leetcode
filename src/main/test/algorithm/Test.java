package algorithm;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2020/12/25 0:02
 */
public class Test {

    public static void main(String[] args) {

    }

    public int result(int m, int n) {

        int times = 0;
        int x = 0;
        while (x < n) {

            x += Math.min(m, n);
            times++;
        }
        return times;
    }
}
