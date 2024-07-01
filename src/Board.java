import java.util.ArrayList;
import java.util.List;

public class Board {
    private String[][] board;

    public Board() {
        board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
    }

    public boolean isWinner(String player) {
        return (board[0][0].equals(player) && board[0][1].equals(player) && board[0][2].equals(player)) ||
                (board[1][0].equals(player) && board[1][1].equals(player) && board[1][2].equals(player)) ||
                (board[2][0].equals(player) && board[2][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][0].equals(player) && board[1][0].equals(player) && board[2][0].equals(player)) ||
                (board[0][1].equals(player) && board[1][1].equals(player) && board[2][1].equals(player)) ||
                (board[0][2].equals(player) && board[1][2].equals(player) && board[2][2].equals(player)) ||
                (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player));
    }

    public boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    public void makeMove(int row, int col, String player) {
        board[row][col] = player;
    }

    public void undoMove(int row, int col) {
        board[row][col] = "";
    }
}


