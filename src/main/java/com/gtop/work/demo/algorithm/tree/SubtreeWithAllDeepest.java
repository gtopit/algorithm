package com.gtop.work.demo.algorithm.tree;

/**
 * @author hongzw@citycloud.com.cn
 */
public class SubtreeWithAllDeepest {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(btreeGameWinningMove(root, 3, 2));
    }


    public static boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode xNode = getNodeByValue(root, x);
        int lNum = getNumberOfNode(xNode.left);
        int rNum = getNumberOfNode(xNode.right);
        int oNum = n - lNum - rNum - 1;
        if (oNum > lNum + rNum + 1) {
            return true;
        }
        if (lNum > oNum + rNum + 1) {
            return true;
        }
        if (rNum > oNum + lNum + 1) {
            return true;
        }
        return false;
    }

    private static int getNumberOfNode(TreeNode node) {

        if (node == null) {
            return 0;
        }
        int ln = getNumberOfNode(node.left);
        int rn = getNumberOfNode(node.right);

        return ln + rn + 1;
    }

    private static TreeNode getNodeByValue(TreeNode root, int x) {

        if (root == null) {
            return null;
        }

        if (root.val == x) {
            return root;
        }
        TreeNode node = getNodeByValue(root.left, x);
        if (node != null) {
            return node;
        }
        node = getNodeByValue(root.right, x);
        if (node != null) {
            return node;
        }
        return null;
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
