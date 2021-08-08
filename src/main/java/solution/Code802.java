package solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author makise
 * @version 1.0
 * @date 2021/8/5 23:13
 */
public class Code802 {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        List<Integer> ans = new ArrayList<>();
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
                if (isSafe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }


    public boolean isSafe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        //0初始 1在环或者迭代中 2安全节点
        color[x] = 1;
        for (int y : graph[x]) {
            if (!isSafe(graph, color, y)) {
                return false;
            }
        }

        color[x] = 2;
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
//                {1,2},{},{}
        };

        Code802 code802 = new Code802();

        System.out.println(code802.eventualSafeNodes(graph));
    }
}
