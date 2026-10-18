package projects.ticTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        PlayerStrategy p1 = playerFactory.getPlayer("human");
        p1.setSymbol(Symbol.X);
        PlayerStrategy xP = p1;
        PlayerStrategy p2 = playerFactory.getPlayer("human");
        p2.setSymbol(Symbol.O);
        PlayerStrategy oP = p2;
        TurnStateContext context = new TurnStateContext(new Xstate());
        Board board = new Board(3, context);
        xP.setBoard(board);
        oP.setBoard(board);
        Scanner input = new Scanner(System.in);
        GameState gameState = GameState.IN_PROGRESS;
        while (true) {
            if (gameState != GameState.IN_PROGRESS) {
                if (gameState == GameState.WON) {
                    System.out.println("Congratulations! You win!: " + context.getState().getSymbol().toString());
                } else System.out.println("DRAW");
                break;
            }
            int r = input.nextInt();
            int c = input.nextInt();
            if (context.getState().getSymbol() == Symbol.X) {
                xP.setTurnState(context.getState());
                gameState = xP.makeMove(r, c);
            } else {
                oP.setTurnState(context.getState());
                gameState = oP.makeMove(r, c);
            }
        }
    }
}
