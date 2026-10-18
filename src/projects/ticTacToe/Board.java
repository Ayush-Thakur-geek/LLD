package projects.ticTacToe;

import java.util.Arrays;

public class Board {
    int n;
    private final BlockState[][] grid;
    private int[] rows;
    private int[] cols;
    private int diag;
    private int antiDiag;
    private int emptyCount;
    private TurnStateContext context;
    Board(int n, TurnStateContext context) {
        this.grid = new BlockState[n][n];
        this.n = n;
        this.emptyCount = n*n;
        this.rows = new int[n];
        this.cols = new int[n];
        this.context = context;
        this.diag = 0;
        this.antiDiag = 0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(this.grid[i], BlockState.EMPTY);
        }
    }

    public GameState validateMove(int r, int c, PlayerStrategy player, TurnState turnState) {
        if (
                r >= 0 && c >= 0 && r < n &&
                        c < n && this.grid[r][c] == BlockState.EMPTY &&
                        player.getSymbol() == turnState.getSymbol()
        ) {
            System.out.println("Valid Move");
            return move(r, c, player);
        } else {
            System.out.println("Invalid Move, Try again");
            return GameState.IN_PROGRESS;
        }
    }

    private GameState move(int r, int c, PlayerStrategy player) {
        --emptyCount;
        BlockState blockState;
        int toBeAdded;
        if (player.getSymbol() == Symbol.X) {
            blockState = BlockState.X;
            toBeAdded = 1;
        } else {
            blockState = BlockState.O;
            toBeAdded = -1;
        }
        this.grid[r][c] = blockState;
        rows[r] += toBeAdded;
        cols[c] += toBeAdded;
        if (r == c) {
            diag += toBeAdded;
        }
        if (r + c == n - 1) {
            antiDiag += toBeAdded;
        }

        if (Math.abs(rows[r]) == n
                || Math.abs(cols[c]) == n
                || Math.abs(diag) == n
                || Math.abs(antiDiag) == n) {
            return GameState.WON;
        } else {
            if (emptyCount == 0) return GameState.DRAW;
            else {
                this.context.next();
                return GameState.IN_PROGRESS;
            }
        }
    }
}
