package com.yzb.test.test;

import com.yzb.test.ds.UnionFind.UnionFind6;

import java.util.HashSet;
import java.util.Set;

public class Test12 {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1' , '1', '0'},
                {'0' , '1', '0'},
                {'1' , '1', '1'}
        };

        int ret = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == '1') {
                    grid[i][j] = '2';
                    if (i >= 1 && grid[i - 1][j] == '2'
                        || j >= 1 && grid[i][j - 1] == '2') {
                    }
                    else {
                        ret++;
                    }
                }
            }
        }

        System.out.println(ret);
    }
}
