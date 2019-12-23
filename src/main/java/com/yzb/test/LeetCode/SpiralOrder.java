package com.yzb.test.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        int[][] matrix2 = {
                {2, 3, 4},
                {5, 6, 7},
                {8, 9, 10},
                {11, 12, 13},
                {14, 15, 16},
        };
        System.out.println(spiralOrder(matrix2));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int m  = matrix.length;
        if (m == 0) return list;
        int n = matrix[0].length;
        if (n == 0) return list;
        if (n == 1) {
            for (int i = 0; i < m; i++) {
                list.add(matrix[i][0]);
            }
            return list;
        }

        for (int i = 0; i < m / 2; i++) {
            if (i + 1 == n) break;
            if (n - 2 * i <= 0) break;

            for (int j = i; j < n - i; j++) {
                list.add(matrix[i][j]);
            }
            for (int j = i + 1; j < m - i - 1; j++) {
                list.add(matrix[j][n - 1 - i]);
            }
            for (int j = n - i - 1; j >= i && m - i - 1 > i; j--) {
                list.add(matrix[m - i - 1][j]);
            }
            for (int j = m - i - 2; j > i && i < n - 1 - i; j--) {
                list.add(matrix[j][i]);
            }
        }

//        if (m % 2 == 1) {
//            int i = m / 2;
//            for (int j = i; j < n - i; j++) {
//                list.add(matrix[i][j]);
//            }
//        }

        return list;
    }
}
