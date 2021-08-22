package solution;

import org.junit.Assert;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/19 9:37
 */
public class Code345 {


    public String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";

        char[] chars = s.toCharArray();

        int left = 0, right = s.length() - 1;

        while (left < right) {
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                ++left;
            }
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                --right;
            }
            if (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                ++left;
                --right;
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        Code345 code345 = new Code345();
        Assert.assertEquals(code345.reverseVowels("aeiouaeiou"), "uoieauoiea");
        Assert.assertEquals(code345.reverseVowels(""), "");
        Assert.assertEquals(code345.reverseVowels("hello"), "holle");
        Assert.assertEquals(code345.reverseVowels("leetcode"), "leotcede");
    }
}
