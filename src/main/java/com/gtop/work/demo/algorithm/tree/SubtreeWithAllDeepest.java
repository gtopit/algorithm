package com.gtop.work.demo.algorithm.tree;

/**
 * @author hongzw@citycloud.com.cn
 */
public class SubtreeWithAllDeepest {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        TreeNode treeNode = subtreeWithAllDeepest(root);
        System.out.println(treeNode.val);
        System.out.println(maxDeep(root));
    }

    public static int maxDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int li = maxDeep(root.left);
        int ri = maxDeep(root.right);
        return Math.max(li + 1, ri + 1);
    }

    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info ans = dp(root);
        return ans.cur;
    }

    private static Info dp(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new Info();
        }
        Info info = new Info();
        info.curDeep = 1;
        info.cur = root;
        Info li = dp(root.left);
        Info ri = dp(root.right);

        return info;
    }

    public static class Info {
        int curDeep;
        TreeNode cur;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

}
