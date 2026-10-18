package projects.ticTacToe;

public class PlayerFactory {
    public PlayerFactory() {

    }
    public PlayerStrategy getPlayer(String playerType) {
        switch (playerType) {
            case "human":
                return new HumanPlayer();

        }
        throw new IllegalArgumentException(
                "Unsupported player type: " + playerType);
    }
}
