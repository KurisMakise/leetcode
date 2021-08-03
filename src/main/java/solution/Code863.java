package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/28 22:24
 */
public class Code863 {
    Map<Integer, TreeNode> parentMap = new HashMap<>();
    List<Integer> ret = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParent(root);
        findAns(target, null, k, 0);
        return ret;
    }

    private void findParent(TreeNode root) {
        if (root.left != null) {
            parentMap.put(root.left.val, root);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
        }
    }

    private void findAns(TreeNode target, TreeNode from, int k, int depth) {
        if (target == null) {
            return;
        }
        if (k == depth) {
            ret.add(target.val);
            return;
        }

        if (target.left != from) {
            findAns(target.left, target.left, k, depth + 1);
        }
        if (target.right != from) {
            findAns(target.right, target.right, k, depth + 1);
        }
        if (parentMap.get(target.val) != from) {
            findAns(parentMap.get(target.val), parentMap.get(target.val), k, depth + 1);
        }

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


        Code863 code671 = new Code863();
        System.out.println(code671.distanceK(root, t2, 1));

    }
}
