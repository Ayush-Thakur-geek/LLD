package projects.ticTacToe;

public interface PlayerStrategy {
    public void setSymbol(Symbol symbol);
    public Symbol getSymbol();
    public GameState makeMove(int r, int c);
    public void setBoard(Board board);
    public void setTurnState(TurnState turnState);
}
