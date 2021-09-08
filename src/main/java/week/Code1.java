package week;

import java.util.Arrays;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/5 11:07
 */
public class Code1 {

    public int countQuadruplets(int[] nums) {

        int ans = 0;
        int end = nums.length;
        for (int i = 0; i < end - 2; ++i) {
            for (int j = i + 1; j < end - 1; ++j) {
                for (int k = j + 1; k < end; ++k) {
                    int target = nums[i] + nums[j] + nums[k];
                    if (target > 100) {
                        continue;
                    }

                    int tmp = end - 1;
                    for (; tmp > k; --tmp) {
                        if (nums[tmp] == target) {
                            ++ans;
                        }
                    }
                }
            }
        }

        return ans;
    }

    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o2[0] - o1[0];
            }
        });


        int ans = 0;
        int maxDefense = 0;
        for (int[] property : properties) {
            int defense = property[1];
            if (defense < maxDefense) {
                ++ans;
            } else {
                maxDefense = defense;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Code1 code1 = new Code1();
        int[][] nums = new int[][]
                {{5, 5}, {6, 3}, {3, 6}, {6, 2}, {6, 1}};
        System.out.println(code1.numberOfWeakCharacters(nums));
    }
}
