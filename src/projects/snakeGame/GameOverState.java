package projects.snakeGame;

public class GameOverState implements GameState {

    @Override
    public void next(GameStateContext context) {
        context.setGameState(new NotStartedState());
        System.out.println("State set to not-started");
    }

    @Override
    public String getState() {
        return GameStates.GAME_OVER.toString();
    }
}
