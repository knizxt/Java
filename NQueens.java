import java.util.*;

public class NQueens {
    static int N;
    static int[] board;
    static int solutions = 0;

    static boolean isSafe(int row, int col) {
        for (int r = 0; r < row; r++) {
            if (board[r] == col || Math.abs(board[r] - col) == Math.abs(r - row))
                return false;
        }
        return true;
    }

    static void solve(int row) {
        if (row == N) {
            solutions++;
            System.out.println(Arrays.toString(board));
            return;
        }
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row] = col;
                solve(row + 1);
                board[row] = -1;
            }
        }
    }

    public static void main(String[] args) {
        N = 6;
        board = new int[N];
        Arrays.fill(board, -1);
        solve(0);
        System.out.println("Total solutions for " + N + "-queens: " + solutions);
    }
}
