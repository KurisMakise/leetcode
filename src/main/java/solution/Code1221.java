package solution;

import java.util.Stack;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/7 22:28
 */
public class Code1221 {


    public int balancedStringSplit(String s) {
        int ans = 1;

        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        int n = chars.length;
        char c = chars[0];
        stack.push(c);
        for (int i = 1; i < n; ++i) {
            if (stack.isEmpty()) {
                ++ans;
                c = chars[i];
                stack.push(chars[i]);
            } else {
                if (chars[i] == c) {
                    stack.push(c);
                } else {
                    stack.pop();
                }
            }

        }

        return ans;
    }


    public int balancedStringSplit1(String s) {
        int ans = 0;
        int d = 0;

        char[] chars = s.toCharArray();

        for (char aChar : chars) {
            if (aChar == 'L') {
                ++d;
            } else {
                --d;
            }
            if (d == 0) {
                ++ans;
            }
        }

        return ans;
    }
}
