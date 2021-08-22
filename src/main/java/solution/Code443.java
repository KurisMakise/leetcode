package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/21 19:55
 */
public class Code443 {

    public int compress(char[] chars) {
        int n = chars.length;

        int index = 0;
        int nums = 1;
        //tmp == chars[i] nums++;
        //tmp != chars[i]  tmp=chars[i]  if nums>1  chars[i] = nums    ++ans
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || chars[i] != chars[i + 1]) {
                chars[index++] = chars[i];
                if (nums > 1 && nums <= 9) {
                    chars[index++] = (char) (nums + '0');
                } else if (nums > 9) {
                    char[] numsArray = Integer.toString(nums).toCharArray();
                    for (char c : numsArray) {
                        chars[index++] = c;
                    }
                }
                nums = 1;
            } else if (chars[i] == chars[i + 1]) {
                ++nums;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Code443 code443 = new Code443();
        char[] chars = {'v', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'};
        char[] B = {'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(code443.compress(chars));
        System.out.println(code443.compress(B));
    }
}
