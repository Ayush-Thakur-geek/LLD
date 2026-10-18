package projects.ticTacToe;

public interface TurnState {
    public void nextState(TurnStateContext context);
    public Symbol getSymbol();
}
