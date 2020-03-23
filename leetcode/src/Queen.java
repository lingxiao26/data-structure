import java.util.Arrays;
import java.util.List;

public class Queen {

    // chessboard: 棋盘  (r, c): 皇后在棋盘中的位置, r行c列 row column
    // 把皇后所在位置的八个方向全部标记为1
    public static void putDownTheQueen(int[][] chessboard, StringBuilder[] sbs, int x, int y) {
        // 两个方向数组
        int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
        int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

        chessboard[x][y] = 8; // 皇后所在的位置置为1

        for ( int i = 1; i < chessboard.length; i ++ ) {
            for ( int j = 0; j < 8; j ++ ) {
                // 通过皇后的位置计算出八个方向的位置[u, v]
                // i表示以(x, y)为起点,每次走多少步
                // (x, y) + (dx[j], dy[j]) = 往某个方向走了一步, dx, dy定义了8个方向
                int u = x + i * dx[j];
                int v = y + i * dy[j];

                if ( u < chessboard.length && u >= 0 &&
                     v < chessboard.length && v >= 0 ) {
                    chessboard[u][v] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[4][4];
        StringBuilder[] sbs = new StringBuilder[4];

        putDownTheQueen(arr, sbs, 1, 1);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
