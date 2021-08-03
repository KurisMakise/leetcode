package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/23 22:25
 */
public class Code165 {

    public static int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        while (i < version1.length() || j < version2.length()) {
            int a = 0, b = 0;
            for (; i < version1.length(); i++) {
                if (version1.charAt(i) != '.') {
                    a = a * 10 + version1.charAt(i) - '0';
                } else {
                    i++;
                    break;
                }
            }
            for (; j < version2.length(); j++) {
                if (version2.charAt(j) != '.') {
                    b = b * 10 + version2.charAt(j) - '0';
                } else {
                    j++;
                    break;
                }
            }
            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Code165.compareVersion("1.01", "1"));
    }
}
