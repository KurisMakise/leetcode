package solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/16 19:17
 */
public class Code526 {

    boolean[] vis;
    int ans;

    List<List<Integer>> match;

    public int countArrangement(int n) {
        vis = new boolean[n + 1];
        for (int i = 0; i < n; ++i) {
            match = new ArrayList<>();
        }

        for (int i = 1; i <= n; ++i) {
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= n; ++j) {
                if (i % j == 0 || j % i == 0) {
                    list.add(j);
                }
            }
            match.add(list);
        }

        ans = 0;
        backtrack(1, n);
        return ans;
    }

    public void backtrack(int index, int length) {
        if (index == length + 1) {
            ++ans;
            return;
        }
        for (int j : match.get(index - 1)) {
            if (!vis[j]) {
                vis[j] = true;
                backtrack(index + 1, length);
                vis[j] = false;
            }
        }
    }

    /**
     * @param index  插入的下标
     * @param length 长度
     */
    public void backtrack1(int index, int length) {
        if (index == length + 1) {
            ++ans;
            return;
        }
        //第 i 位的数字能被 i 整除
        //i 能被第 i 位上的数字整除
        for (int num = 1; num <= length; num++) {
            if (!vis[num]) {
                if (index % num == 0 || num % index == 0) {

                    vis[num] = true;
                    backtrack(index + 1, length);
                    vis[num] = false;
                }
            }
        }
    }


    public static void main(String[] args) {
        //1 2 3  3 2 1   2 1 3

        Code526 code526 = new Code526();
        System.out.println(code526.countArrangement(3));
        System.out.println(code526.countArrangement(4));
        System.out.println(code526.countArrangement(5));
        System.out.println(code526.countArrangement(6));
        System.out.println(code526.countArrangement(7));
        System.out.println(code526.countArrangement(8));
        System.out.println(code526.countArrangement(9));
    }
}
