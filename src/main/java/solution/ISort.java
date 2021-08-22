package solution;

import java.util.Comparator;
import java.util.List;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/17 14:37
 */
public interface ISort {

    <E> List<E> sort(List<E> nums, Comparator<E> comparator) throws Exception;
}
