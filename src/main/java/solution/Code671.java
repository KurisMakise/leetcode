package solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/27 21:51
 */
public class Code671 {

    public int findSecondMinimumValue(TreeNode root) {

        return preOrder(root, root.val);
    }

    public int level(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int second = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.val != root.val) {
                if (second == -1) {
                    second = node.val;
                }
                if (node.val < second) {
                    second = node.val;
                }
            }

            if (node.left == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);

        }
        return second;
    }

    public int preOrder(TreeNode node, int min) {
        if (node == null) {
            return -1;
        }

        if (node.val > min) {
            return node.val;
        }

        int left = preOrder(node.left, min);
        int right = preOrder(node.right, min);

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }

        return Math.max(left, right);
    }

    public int inOrder(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return -1;
    }

    public int postOrder(TreeNode node) {
        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(1);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(5);

        root.left = t1;
        root.right = t2;

        root.left.left = t5;
        root.left.right = t3;

        root.right.left = t6;
        root.right.right = t4;


        Code671 code671 = new Code671();
        int secondMinimumValue = code671.findSecondMinimumValue(root);
        System.out.println(secondMinimumValue);

    }
}
