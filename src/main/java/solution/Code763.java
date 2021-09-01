package solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/26 15:05
 */
public class Code763 {

    public List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();

        int[] position = new int[26];
        for (int i = 0; i < chars.length; ++i) {
            position[chars[i] - 'a'] = i;
        }

        List<Integer> ans = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < chars.length; ++i) {
            int p = position[chars[i] - 'a'];
            if (end < p) {
                end = p;
            }
            if (i == end) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Code763 code763 = new Code763();
        System.out.println(code763.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
