package solution;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/3 21:27
 */
public class Expression {


    public static final String REGEX = "\\{\\{([\\s\\S]*?)}}";

    public String replace(String content, Map<String, String> replaceWordMap) {
        if (content == null) {
            return null;
        }

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(content);

        StringBuilder resBuilder = new StringBuilder();

        int index = 0;
        while (matcher.find()) {
            String matchKey = matcher.group();
            matchKey = matchKey.substring(2, matchKey.length() - 2).trim();
            String replaceWord = replaceWordMap.get(matchKey);

            if (replaceWord != null) {
                resBuilder.append(content, index, matcher.start()).append(replaceWord);
                index = matcher.end();
            }
        }

        resBuilder.append(content, index, content.length());

        return resBuilder.toString();
    }

    public static void main(String[] args) {
        Expression expression = new Expression();
        Map<String, String> map = new HashMap<>(5);
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("哈哈k1", "v3哈哈");
        System.out.println(expression.replace("this is {{ 哈哈k1}} {{ k2}} 22 3", map));
        System.out.println(expression.replace("this is {{ k1}}", map));
        System.out.println(expression.replace("this is {{k1 }}", map));
        System.out.println(expression.replace("this is {{ k1 }}", map));
        System.out.println(expression.replace("this is {{ k1 }", map));
        System.out.println(expression.replace("this is { k1 }}", map));
        System.out.println(expression.replace("this is  k1 }}", map));
        System.out.println(expression.replace("this is  {{ k1 }", map));
    }

}
