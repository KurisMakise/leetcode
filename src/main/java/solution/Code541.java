package solution;

import org.junit.Assert;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/20 10:39
 */
public class Code541 {


    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = s.length();

        int index = 0;
        while (index < n) {
            int end = Math.min(index + k, n);
            swap(chars, index, end - 1);
            index += 2 * k;
        }
        return new String(chars);
    }

    private void swap(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            ++start;
            --end;
        }
    }

    public static void main(String[] args) {
        Code541 code541 = new Code541();
        Assert.assertEquals("bacdfeg", code541.reverseStr("abcdefg", 2));
        Assert.assertEquals("bacd", code541.reverseStr("abcd", 2));
        Assert.assertEquals("a", code541.reverseStr("a", 1));
        Assert.assertEquals("ab", code541.reverseStr("ba", 2));
    }
}
