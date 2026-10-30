package projects.snakeGame;

public interface GameState {
    void next(GameStateContext context);
    String getState();
}
