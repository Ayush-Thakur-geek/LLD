package projects.ticTacToe;

public class Ostate implements TurnState{
    public Symbol symbol;
    private TurnState next;
    public Ostate() {
        this.symbol = Symbol.O;
    }

    @Override
    public void nextState(TurnStateContext context) {
        context.setState(new Xstate());
    }

    @Override
    public Symbol getSymbol() {
        return Symbol.O;
    }
}
