public class AI {
    private String aiPlayer;
    private String humanPlayer;

    public AI(String aiPlayer, String humanPlayer) {
        this.aiPlayer = aiPlayer;
        this.humanPlayer = humanPlayer;
    }

    public int[] bestMove(Board board) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int[] move : board.getAvailableMoves()) {
            board.makeMove(move[0], move[1], aiPlayer);
            int moveVal = minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            board.undoMove(move[0], move[1]);

            if (moveVal > bestVal) {
                bestMove = move;
                bestVal = moveVal;
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing, int alpha, int beta) {
        if (board.isWinner(aiPlayer)) return 10 - depth;
        if (board.isWinner(humanPlayer)) return depth - 10;
        if (board.isDraw()) return 0;

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] move : board.getAvailableMoves()) {
                board.makeMove(move[0], move[1], aiPlayer);
                int eval = minimax(board, depth + 1, false, alpha, beta);
                board.undoMove(move[0], move[1]);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] move : board.getAvailableMoves()) {
                board.makeMove(move[0], move[1], humanPlayer);
                int eval = minimax(board, depth + 1, true, alpha, beta);
                board.undoMove(move[0], move[1]);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            return minEval;
        }
    }
}