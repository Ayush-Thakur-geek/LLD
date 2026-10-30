package projects.snakeGame;

public class NotStartedState implements GameState {
    @Override
    public void next(GameStateContext context) {
        context.setGameState(new RunningState());
        System.out.println("State set to running");
    }

    @Override
    public String getState() {
        return GameStates.NOT_STARTED.toString();
    }
}
