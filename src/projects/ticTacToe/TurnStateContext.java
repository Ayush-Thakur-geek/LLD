package projects.ticTacToe;

public class TurnStateContext {
    private TurnState state;
    TurnStateContext(TurnState state) {
        this.state = state;
    }
    public void setState(TurnState state) {
        this.state = state;
    }
    public TurnState getState() {
        return state;
    }
    public void next() {
        this.state.nextState(this);
    }
}
