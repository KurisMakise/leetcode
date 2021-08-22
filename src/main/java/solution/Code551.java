package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/17 9:28
 */
public class Code551 {

    public boolean checkRecord(String s) {
        int countLate = 0;
        int countAbsent = 0;

        for (int i = 0; i < s.length(); i++) {
            if (countAbsent == 2 || countLate == 3) {
                return false;
            }
            char c = s.charAt(i);
            if (c == 'A') {
                ++countAbsent;
                countLate = 0;
            } else if (c == 'L') {
                ++countLate;
            } else {
                countLate = 0;
            }
        }
        return true;
    }
}
