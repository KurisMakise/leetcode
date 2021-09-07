package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/5 23:49
 */
public class Code470 {
    public int rand10() {
        int ans;
        do {
            int x = rand7();
            int y = rand7();
            ans = x + (y - 1) * 7;
        } while (ans > 40);
        return ans % 10 + 1;
    }

    private int rand7() {
        return 0;
    }

}
