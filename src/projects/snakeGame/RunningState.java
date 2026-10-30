package projects.snakeGame;

public class RunningState implements GameState{

    @Override
    public void next(GameStateContext context) {
        context.setGameState(new GameOverState());
        System.out.println("State set to over");
    }

    @Override
    public String getState() {
        return GameStates.RUNNING.toString();
    }
}
