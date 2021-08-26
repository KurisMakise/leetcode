package solution;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/26 0:08
 */
public class Code881 {


    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int left = 0, right = n - 1;
        int ans = 0;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                ++left;
            }
            ++ans;
            --right;
        }

        return ans;
    }

    public int numRescueBoats1(int[] people, int limit) {

        int[] buckets = new int[limit + 1];
        for (int p : people) {
            ++buckets[p];
        }

        int left = 1, right = limit - 1;

        int ans = buckets[limit];
        while (left <= right) {
            while (left <= right && buckets[left] == 0) {
                ++left;
            }
            while (left <= right && buckets[right] == 0) {
                --right;
            }
            if (left + right > limit) {
                ans += buckets[right];
                --right;
                continue;
            }

            if (left == right) {
                ans += buckets[left] / 2 + buckets[left] % 2;
                ++left;
            } else if (buckets[left] == buckets[right]) {
                ans += buckets[left];
                ++left;
                --right;
            } else if (buckets[left] > buckets[right]) {
                ans += buckets[right];
                buckets[left] -= buckets[right];
                --right;
            } else {
                ans += buckets[left];
                buckets[right] -= buckets[left];
                ++left;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Code881 code881 = new Code881();
        int[] people = new int[]{
                7, 3, 2
        };

        System.out.println(code881.numRescueBoats(people, 8));
        System.out.println(code881.numRescueBoats1(people, 8));
    }
}
