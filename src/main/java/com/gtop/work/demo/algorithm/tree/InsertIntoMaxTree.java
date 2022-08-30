package com.gtop.work.demo.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public class InsertIntoMaxTree {

    public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        List<Integer> arr = treeNodeToArr(root, val);
        return constructMaximumBinaryTree(arr);
    }

    private static List<Integer> treeNodeToArr(TreeNode root, int val) {
        List<Integer> list = new ArrayList<>();
        midTraver(root, list);
        list.add(val);
        return list;
    }

    private static void midTraver(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        midTraver(root.left, list);
        list.add(root.val);
        midTraver(root.right, list);
    }

    public static TreeNode constructMaximumBinaryTree(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return null;
        }
        return buildTree(nums,0, nums.size() - 1);
    }

    private static TreeNode buildTree(List<Integer> nums, int left, int right) {
        int index = getMaxValueIndexOfArr(nums, left, right);
        if (index < 0 || index > right) {
            return null;
        }
        TreeNode root = new TreeNode(nums.get(index));
        root.left = buildTree(nums, left, index - 1);
        root.right = buildTree(nums, index + 1, right);
        return root;
    }

    private static int getMaxValueIndexOfArr(List<Integer> nums, int left, int right) {
        int index = left;
        for (int i = left + 1; i <= right; i++) {
            index = nums.get(i) > nums.get(index) ? i : index;
        }
        return index;
    }

}
