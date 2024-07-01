import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private Board board = new Board();
    private AI ai = new AI("O", "X");

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private void makeAIMove() {
        int[] move = ai.bestMove(board);
        buttons[move[0]][move[1]].setText("O");
        buttons[move[0]][move[1]].setEnabled(false);
        board.makeMove(move[0], move[1], "O");
        checkGameState();
    }

    private void checkGameState() {
        if (board.isWinner("O")) {
            JOptionPane.showMessageDialog(this, "AI wins!");
            resetBoard();
        } else if (board.isWinner("X")) {
            JOptionPane.showMessageDialog(this, "Human wins!");
            resetBoard();
        } else if (board.isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private void resetBoard() {
        board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttons[row][col].setText("X");
            buttons[row][col].setEnabled(false);
            board.makeMove(row, col, "X");
            checkGameState();
            if (!board.isDraw() && !board.isWinner("X") && !board.isWinner("O")) {
                makeAIMove();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
