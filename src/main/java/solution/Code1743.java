package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/25 20:11
 */
public class Code1743 {

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            map.putIfAbsent(adjacentPair[0], new ArrayList<>());
            map.putIfAbsent(adjacentPair[1], new ArrayList<>());

            map.get(adjacentPair[0]).add(adjacentPair[1]);
            map.get(adjacentPair[1]).add(adjacentPair[0]);
        }

        int[] ret = new int[adjacentPairs.length + 1];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
//            ret[entry.getKey()] = entry.getValue();
        }
        return null;
    }
}
