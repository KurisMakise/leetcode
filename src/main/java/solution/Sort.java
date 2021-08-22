package solution;

import java.util.Comparator;
import java.util.List;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/17 14:24
 */
public class Sort implements ISort {
    @Override
    public <E> List<E> sort(List<E> nums, Comparator<E> comparator) throws Exception {
        if (comparator == null) {

            throw new Exception();
        }
        return quickSort(nums, 0, nums.size(), comparator);
    }

    public <E> List<E> quickSort(List<E> nums, int left, int right, Comparator<E> comparator) {
        if (left >= right) {
            return nums;
        }
        E compare = nums.get(left);

        int i = left, j = right;

        while (i < j) {
            while (i < j && comparator.compare(nums.get(i), compare) < 0) {
                ++i;
            }
            while (i < j && comparator.compare(nums.get(j), compare) > 0) {
                ++j;
            }
            if (comparator.compare(nums.get(i), nums.get(j)) == 0) {
                ++i;
            } else {
                E tmp = nums.get(i);
                nums.remove(i);
                nums.add(i, nums.get(j));
                nums.remove(j);
                nums.add(j, tmp);
            }
        }

        quickSort(nums, left, i - 1, comparator);
        quickSort(nums, j + 1, right, comparator);
        return nums;
    }


}
