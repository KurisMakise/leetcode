package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/24 20:56
 */
public class Code1736 {

    public static String maximumTime(String time) {
        char[] array = time.toCharArray();
        int remain = 23 - getInt(array[0]) * 10 - getInt(array[1]);

        if (array[0] == '?') {
            int min = Math.min(remain, 20);
            array[0] = (char) (min / 10 + '0');
            remain -= min;
        }
        if (array[1] == '?') {
            array[1] = (char) (Math.min(remain, 9) + '0');
        }
        if (array[3] == '?') {
            array[3] = '5';
        }
        if (array[4] == '?') {
            array[4] = '9';
        }
        return new String(array);
    }

    public static int getInt(char c) {
        return c == '?' ? 0 : c - '0';
    }

    public static void main(String[] args) {
        System.out.println(Code1736.maximumTime("?4:03"));
    }
}
