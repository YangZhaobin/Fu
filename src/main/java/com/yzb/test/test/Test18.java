package com.yzb.test.test;

import java.util.ArrayList;
import java.util.List;

public class Test18 {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        triangle.add(list);

        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        triangle.add(list);

        list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(7);
        triangle.add(list);

        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(8);
        list.add(3);
        triangle.add(list);
        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) return 0;
        int m = triangle.size();
        if (m == 0) return 0;

        int[][] path = new int[m][m];

        for (int i = 0; i < m; i++) {
            List<Integer> row = triangle.get(i);
            int size = row.size();
            if (size == 1) {
                path[i][i] = row.get(0);
                System.out.println("i: " + i + "  j: "+  i + "    " + path[i][i]);
                continue;
            }
            for (int j = 0; j < size; j++) {
                if (j == 0) {
                    path[i][j] = path[i - 1][j] + row.get(j);
                }
                else if (j == size - 1) {
                    path[i][j] = path[i - 1][j - 1] + row.get(j);
                }
                else {
                    path[i][j] = Math.min(path[i - 1][j - 1], path[i - 1][j]) + row.get(j);
                }
                System.out.println("i: " + i + "  j: "+  j + "    " + path[i][j]);
            }
        }

        int min = path[m - 1][0];
        for (int i = 1; i < m; i++) {
            min = min < path[m - 1][i] ? min : path[m - 1][i];
        }

        print(path);
        return min;
    }

    
    public static void print(int[][] path) {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
