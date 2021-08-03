package solution;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author violet
 * @version 1.0
 * @since 2020/1/9 22:44
 */
public class TreeNode {
    int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode() {
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
