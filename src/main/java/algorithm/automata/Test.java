package algorithm.automata;

import java.util.*;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/7/19 17:50
 */
public class Test {

    public static void main(String[] args) {
        String[] words = new String[]{
                "人人啊人人",
                "人中国",
        };
        String key = "人";

        Map<Float, String> map = new HashMap<>();

        for (String word : words) {
            map.put(calWeight(word, key), word);
        }
        System.out.println(sort(map));
    }

    private static List<String> sort(Map<Float, String> map) {
        List<Float> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        System.out.println(keys);
        List<String> sortedWords = new ArrayList<>(keys.size());
        for (int i = keys.size() - 1; i >= 0; i--) {
            sortedWords.add(map.get(keys.get(i)));
        }
        return sortedWords;
    }

    private static float calWeight(String word, String searchKey) {
        char[] wordArr = word.toCharArray();
        char[] keyArr = searchKey.toCharArray();

        float weight = 1;
        float result = 0;

        int i = 0;
        int j = 0;

        for (; j < wordArr.length; j++) {
            if (keyArr[i] == wordArr[j]) {
                result += weight;
                if (i == keyArr.length - 1) {
                    i = 0;
                } else {
                    i++;
                }
            }
            weight = weight / 2;
        }
        return result;
    }


}
