package com.gtop.work.demo.algorithm.tree;

public class RemoveLeafNodes {

    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        boolean isDelete = nodeIsDelete(root, target);
        if (isDelete) {
            return null;
        }
        return root;
    }

    private static boolean nodeIsDelete(TreeNode root, int target) {

        if (root == null) {
            return true;
        }
        boolean l = nodeIsDelete(root.left, target);
        boolean r = nodeIsDelete(root.right, target);
        if (l) {
            root.left = null;
        }
        if (r) {
            root.right = null;
        }
        if (l && r && root.val == target) {
            return true;
        }
        return false;
    }

}
