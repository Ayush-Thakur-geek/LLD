package projects.ticTacToe;

public class HumanPlayer implements PlayerStrategy {
    private Board board;
    private Symbol symbol;
    private TurnState turnState;

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setTurnState(TurnState turnState) {
        this.turnState = turnState;
    }

    @Override
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public GameState makeMove(int r, int c) {
        System.out.println(this.symbol + " made move to row: " + r + ", col: " + c);
        return board.validateMove(r, c, this, turnState);
    }
}
