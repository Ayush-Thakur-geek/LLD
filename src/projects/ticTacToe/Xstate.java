package projects.ticTacToe;

public class Xstate implements TurnState {
    private TurnState next;
    private final Symbol symbol;
    Xstate() {
        this.symbol = Symbol.X;
    }
    @Override
    public void nextState(TurnStateContext context) {
        context.setState(new Ostate());
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.X;
    }
}
