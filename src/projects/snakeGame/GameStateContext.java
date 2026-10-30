package projects.snakeGame;

public class GameStateContext {
    GameState gameState;
    public GameStateContext() {
        this.gameState = new NotStartedState();
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public void next() {
        this.gameState.next(this);
    }
}
