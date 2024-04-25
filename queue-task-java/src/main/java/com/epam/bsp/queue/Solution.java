package com.epam.bsp.queue;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /**
     * Returns the number of islands in a given grid.
     *
     * @param grid a map of 1 (land) and 0 (water).
     * @return the number of islands in a given grid.
     */
    public static int getIslandsCount(int[][] grid) {
        //Hint: you need to go over the grid and 'explore' islands
        //one by one, some sort of BFS (using queue) will do.
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        boolean[][]visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    bfs(grid, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }
    private static void bfs(int[][] grid, boolean[][] visited, int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        Queue<int []> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        int [][] directions = {{1,0},{-1, 0},{0, 1},{0, -1}};
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : directions) {
                int ni = curr[0] + dir[0];
                int nj = curr[1] + dir[1];
                if(ni<0 || ni >= m || nj<0 || nj >= n){
                    continue;
                }
                if(grid[ni][nj] == 1 && !visited[ni][nj]){
                    queue.offer(new int[]{ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }
    }

    /**
     * Returns the minimum number of knight moves to reach the destination point.
     *
     * @param chessboard a matrix of cells.
     * @return the minimum number of knight moves to reach the destination point.
     */


    public static int getMinimumKnightMoves(char[][] chessboard) {
        int[][] moves = new int[][] {{-1, -2}, {-2, -1}, {1, -2}, {2, -1}, {-1, 2}, {-2, 1}, {1, 2}, {2, 1}}; // all possible moves of a knight
        int n = chessboard.length;
        int m = chessboard[0].length;
        int startX = -1, startY = -1, endX = -1, endY = -1;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (chessboard[i][j] == 'K') {
                    startX = i;
                    startY = j;
                } else if (chessboard[i][j] == 'D') {
                    endX = i;
                    endY = j;
                }
            }
        }
        queue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int steps = curr[2];
            if (x == endX && y == endY) {
                return steps;
            }
            for (int[] move : moves) {
                int newX = x + move[0];
                int newY = y + move[1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    if (chessboard[newX][newY] != 'O' && !visited[newX][newY]) {
                        queue.offer(new int[]{newX, newY, steps + 1});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
            return -1;
    }

}
