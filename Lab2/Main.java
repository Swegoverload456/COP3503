package Lab2;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = 3; // Example input, can be changed
        MagicSquare magicSquare = new MagicSquare(n);
        List<int[][]> magicSquares = magicSquare.findMagicSquares();

        for (int[][] solution : magicSquares) {
            System.out.println("Done!!");
            for (int[] row : solution) {
                System.out.print("[");
                for (int num = 0; num < row.length; num++) {
                    if(num < row.length-1){
                        System.out.print(num + ", ");
                    }
                    else{
                        System.out.print(num + "");
                    }
                }
                System.out.println("]");
            }
            System.out.println();
        }
    }
}

class MagicSquare {
    private int n;
    private int[][] grid;
    private int magicConstant;
    private List<int[][]> solutions;

    public MagicSquare(int n) {
        this.n = n;
        this.grid = new int[n][n];
        this.magicConstant = n * (n * n + 1) / 2;
        this.solutions = new ArrayList<>();
    }

    public List<int[][]> findMagicSquares() {
        backtrack(0, 0);
        return solutions;
    }

    private void backtrack(int row, int col) {
        if (row == n) {
            if (isMagicSquare()) {
                addSolution();
            }
            return;
        }

        for (int num = 1; num <= n * n; num++) {
            if (canPlace(num, row, col)) {
                grid[row][col] = num;
                if (col == n - 1) {
                    backtrack(row + 1, 0);
                } else {
                    backtrack(row, col + 1);
                }
                grid[row][col] = 0; // backtrack
            }
        }
    }
    private boolean canPlace(int num, int row, int col) {
        for (int i = 0; i < n; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isMagicSquare() {
        for (int i = 0; i < n; i++) {
            if (sumRow(i) != magicConstant || sumCol(i) != magicConstant) {
                return false;
            }
        }
        return sumMainDiagonal() == magicConstant && sumAntiDiagonal() == magicConstant;
    }

    private int sumRow(int row) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[row][i];
        }
        return sum;
    }

    private int sumCol(int col) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[i][col];
        }
        return sum;
    }

    private int sumMainDiagonal() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[i][i];
        }
        return sum;
    }

    private int sumAntiDiagonal() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[i][n - i - 1];
        }
        return sum;
    }

    private void addSolution() {
        int[][] solution = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(grid[i], 0, solution[i], 0, n);
        }
        solutions.add(solution);
    }
}