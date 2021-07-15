package solution.searchTree;

/**
 * 二叉搜索树
 *
 * @author violet
 * @version 1.0
 * @date 2020/11/30 17:32
 */
public class BST {
    public static void main(String[] args) {
        BST bst = new BST();
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(1);
        System.out.println(bst.isValidBST(treeNode));
    }
    private  int root;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null) {
            if (root.val <= root.left.val) {
                return false;
            } else {
                isValidBST(root.left);
            }
        }

        if (root.right != null) {
            if (root.val >= root.right.val) {
                return false;
            } else {
                isValidBST(root.right);
            }
        }
        return true;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
