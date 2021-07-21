package algorithm;

import java.util.*;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/18 23:40
 */
public class Trie {
    private Map<Character, Trie> children = new HashMap<>();
    private boolean isEndingChar;

    public void insert(String pattern) {
        Trie tmp = this;
        for (char c : pattern.toCharArray()) {
            if (!tmp.children.containsKey(c)) {
                tmp.children.put(c, new Trie());
            }
            tmp = tmp.children.get(c);
        }
        tmp.isEndingChar = true;
    }


    public boolean find(String search) {
        List<String> result = new ArrayList<>();


        return false;
    }

    public static void main(String[] args) {
        String a = "abcd";
        System.out.println(merge(a, a.length() - 1));
    }

    private static List<String> merge(String text, int index) {
        if (index == 0) {
            return Collections.singletonList(text.charAt(0) + "");
        }

        List<String> list = merge(text, --index);
        List<String> result = new ArrayList<>(list.size());

        for (String r : list) {
            for (int i = r.length(); i >= 0; i--) {
                StringBuilder sb = new StringBuilder(r);
                result.add(sb.insert(i, text.charAt(index + 1)).toString());
            }
        }

        return result;

    }

    public AcNode getRoot() {
        return null;
    }
}
