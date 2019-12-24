package com.yzb.test.ds;

import com.yzb.test.ds.tree.BST;
import com.yzb.test.ds.tree.TreeNode;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = new int[]{5, 3, 6, 8, 4, 2};

        for (int num : nums) {
            bst.add(num);
        }
//        System.out.println(bst.toString());

//        bst.preOrder();
//        System.out.println();
//
//        bst.inOrder();
//        System.out.println();
//        bst.inOrderNR();
//        System.out.println();

        bst.postOrder();
        System.out.println();

//        bst.levelOrder();
//        System.out.println();

//        System.out.println(bst.removeMax());
//        System.out.println(bst.removeMin());
//        System.out.println();
//        bst.preOrder();

//        System.out.println(bst.height());
//        System.out.println(bst.heightNR());

        BST<Integer> bst1 = new BST<>();
        List<Integer> pre = Arrays.asList(5, 3, 2, 4, 6, 8);
        List<Integer> in = Arrays.asList(2, 3, 4, 5, 6, 8);
        TreeNode root = getTreeByPreAndIn(pre, in);
        bst1.setRoot(root);
        bst1.postOrder();
    }


    /**
     * 根据前序遍历和中序遍历 重构树
     */
    static TreeNode getTreeByPreAndIn(List<Integer> pre, List<Integer> in) {
        if (pre == null || pre.size() == 0) {
            return null;
        }
        Integer root = pre.get(0);
        TreeNode<Integer> rootNode = new TreeNode<>(root);

        int rootIndex = in.indexOf(root);
        List<Integer> leftIn = in.subList(0, rootIndex);
        List<Integer> rightIn = in.subList(rootIndex + 1, in.size());

        List<Integer> leftPre = pre.subList(1, leftIn.size() + 1);
        List<Integer> rightPre = pre.subList(leftIn.size() + 1, pre.size());

        rootNode.setLeft(getTreeByPreAndIn(leftPre, leftIn));
        rootNode.setRight(getTreeByPreAndIn(rightPre, rightIn));

        return rootNode;
    }

}
