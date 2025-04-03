package Assignment4;

import java.util.Arrays;

public class TreasureHunt {
    
    public int findMinRiskRecursive(int[][] grid, int row, int col) {
        // Base case: reached the bottom-right corner
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }
        
        // If out of bounds, return a very large number
        if (row >= grid.length || col >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        
        // Current cell's risk
        int currentRisk = grid[row][col];
        
        // Recursively calculate minimum risk by moving right and down
        int rightPath = findMinRiskRecursive(grid, row, col + 1);
        int downPath = findMinRiskRecursive(grid, row + 1, col);
        
        // Return current risk plus minimum of right or down path
        return currentRisk + Math.min(rightPath, downPath);
    }

    public int findMinRiskMemoization(int[][] grid, int row, int col, int[][] memo){

        

        return 0;
    }

    public int findMinRiskTabulation(int[][] grid){

        int dp[][] = new int[grid.length][grid[0].length];

        dp[0][0] = grid[0][0];

        //Row first
        for(int i = 1; i < dp[0].length; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        //Col second
        for(int i = 1; i < dp.length; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        //Row loop
        for(int i = 1; i < dp[0].length; i++){

            //Column loop
            for(int j = 1; j < dp.length; j++){

                int minVal = Integer.MAX_VALUE;

                if(dp[j][i-1] <= dp[j-1][i]){
                    minVal = dp[j][i-1] + grid[j][i];
                }
                else{
                    minVal = dp[j-1][i] + grid[j][i];
                }

                dp[j][i] = minVal;
            }
        }

        return dp[dp.length-1][dp[0].length-1];

    }

}
