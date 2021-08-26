package solution;

import java.util.*;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/25 11:41
 */
public class Code797 {
    private List<List<Integer>> ans = new ArrayList<>();
    private Deque<Integer> stack = new ArrayDeque<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.push(0);
        dfs(graph, 0, graph.length - 1);

        return ans;
    }

    private void dfs(int[][] graph, int from, int to) {
        if (from == to) {
            ans.add(new ArrayList<>(stack));
            return;
        }
        int[] g = graph[from];

        for (int i : g) {
            stack.offerLast(i);
            dfs(graph, i, to);
            stack.removeLast();
        }
    }


    public static void main(String[] args) {
        Code797 code797 = new Code797();
        int[][] graph = new int[][]{
                {1, 2}, {3}, {3}, {}
        };
        int[][] graph1 = new int[][]{
                {4, 3, 1}, {3, 2, 4}, {3}, {4}, {}
        };
        System.out.println(code797.allPathsSourceTarget(graph1));
    }
}
