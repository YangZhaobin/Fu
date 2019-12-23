package com.yzb.test.ds;

import com.yzb.test.ds.tree.BST;

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

//        bst.inOrder();
//        System.out.println();
//        bst.inOrderNR();
//        System.out.println();

//        bst.postOrder();
//        System.out.println();

//        bst.levelOrder();
//        System.out.println();

        System.out.println(bst.removeMax());
        System.out.println(bst.removeMin());
        System.out.println();
        bst.preOrder();
    }
}
