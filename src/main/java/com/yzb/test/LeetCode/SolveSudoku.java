package com.yzb.test.LeetCode;

public class SolveSudoku {
    public static void main(String[] args) {
        String[][] board = {
                {"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}
        };
        char[][] b = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                b[i][j] = board[i][j].charAt(0);
            }
        }

        solveSudoku(b);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(b[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] blocks = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '1';
                int blockIndex = (j + i / 3 * 9) / 3;

                rows[i][num] = cols[j][num] = blocks[blockIndex][num] = true;
            }
        }

        backtrace(board, 0, 0, rows, cols, blocks);
    }

    public static boolean backtrace(char[][] board, int i, int j,
                          boolean[][] rows, boolean[][] cols, boolean[][] blocks) {
        if (i == 9)
            return true;

        if (board[i][j] != '.') {
            return j == 8
                    ? backtrace(board, i + 1, 0, rows, cols, blocks)
                    : backtrace(board, i, j + 1, rows, cols, blocks);
        }
        else {
            int blockIndex = (j + i / 3 * 9) / 3;
            for (int num = 0; num < 9; num++) {
                if (rows[i][num] || cols[j][num] || blocks[blockIndex][num]) {
                    continue;
                }
                else {
                    rows[i][num] = cols[j][num] = blocks[blockIndex][num] = true;
                    board[i][j] = (char)(num + '1');

                    boolean nextRet;
                    if (j == 8)
                        nextRet = backtrace(board, i + 1, 0, rows, cols, blocks);
                    else
                        nextRet = backtrace(board, i, j + 1, rows, cols, blocks);

                    if (nextRet) {
                        return true;
                    }
                    else {
                        rows[i][num] = cols[j][num] = blocks[blockIndex][num] = false;
                        board[i][j] = '.';
                    }
                }
            }
        }
        return false;
    }
}
