import java.util.*;

public class KnightsTour {
    static int N = 6;
    static int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[][] board = new int[N][N];

    // Warnsdorff's heuristic - pick next move with fewest onward moves
    static int getDegree(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == -1) count++;
        }
        return count;
    }

    static boolean solve(int x, int y, int move) {
        if (move == N * N) return true;
        List<int[]> next = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == -1)
                next.add(new int[]{nx, ny, getDegree(nx, ny)});
        }
        next.sort(Comparator.comparingInt(a -> a[2]));
        for (int[] n : next) {
            board[n[0]][n[1]] = move;
            if (solve(n[0], n[1], move + 1)) return true;
            board[n[0]][n[1]] = -1;
        }
        return false;
    }

    public static void main(String[] args) {
        for (int[] r : board) Arrays.fill(r, -1);
        board[0][0] = 0;
        if (solve(0, 0, 1)) {
            for (int[] r : board)
                System.out.println(Arrays.toString(r));
        } else {
            System.out.println("No solution for " + N + "x" + N);
        }
    }
}
