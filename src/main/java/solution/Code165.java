package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/23 22:25
 */
public class Code165 {

    public static int compareVersion(String version1, String version2) {
        int n = version1.length(), m = version2.length();

        int i = 0, j = 0;
        while (i < n || j < m) {
            int a = 0, b = 0;
            for (; i < n && version1.charAt(i) != '.'; ++i) {
                a += a * 10 + version1.charAt(i) - '0';
            }

            for (; j < m && version2.charAt(j) != '.'; ++j) {
                b += b * 10 + version2.charAt(j) - '0';
            }
            if (a != b) {
                return a > b ? 1 : -1;
            }
            ++i;
            ++j;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Code165.compareVersion("1.01", "1"));
    }
}
