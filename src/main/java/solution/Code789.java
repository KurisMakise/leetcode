package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/22 13:57
 */
public class Code789 {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {

        int dist = Math.abs(target[0]) + Math.abs(target[1]);

        for (int[] ghost : ghosts) {
            int ghostDist = Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]);
            if (ghostDist <= dist) {
                return false;
            }
        }

        return true;
    }
}
