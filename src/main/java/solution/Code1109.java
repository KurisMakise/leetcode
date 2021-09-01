package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/31 16:11
 */
public class Code1109 {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];

        for (int[] booking : bookings) {
            int start = booking[0], end = booking[1], seats = booking[2];
            while (start <= end) {
                result[start++ - 1] += seats;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] booking = new int[][]{
                {1, 2, 10}, {2, 3, 20}, {2, 5, 25}
        };
        Code1109 code1109 = new Code1109();
        System.out.println(Arrays.toString(code1109.corpFlightBookings(booking, 5)));
    }
}
