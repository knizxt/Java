import java.util.*;

public class SudokuSolver {

    static char[][] board;

    static boolean isValid(int r, int c, char v) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == v || board[i][c] == v || board[3*(r/3)+i/3][3*(c/3)+i%3] == v)
                return false;
        }
        return true;
    }

    static boolean solve() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    for (char v = '1'; v <= '9'; v++) {
                        if (isValid(r, c, v)) {
                            board[r][c] = v;
                            if (solve()) return true;
                            board[r][c] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        board = new char[][] {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        solve();
        for (char[] row : board)
            System.out.println(new String(row));
    }
}
