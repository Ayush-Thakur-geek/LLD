package projects.snakeGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(6, 6);
        GameStateContext gameStateContext = new GameStateContext();
        Snake snake = new Snake(board, gameStateContext);
        FoodGenerator foodGenerator = new FoodGenerator(board);
        board.addSubscriber(snake);
        board.addSubscriber(foodGenerator);
        Scanner scanner = new Scanner(System.in);
        gameStateContext.next();
        Direction direction = Direction.RIGHT;
        while (true) {
            System.out.println("enter the direction");
            String c = scanner.next();
            if (c.toLowerCase().equals("u") && !direction.equals(Direction.DOWN)) direction = Direction.UP;
            else if (c.toLowerCase().equals("d") && !direction.equals(Direction.UP)) direction = Direction.DOWN;
            else if (c.toLowerCase().equals("r") && !direction.equals(Direction.RIGHT)) direction = Direction.LEFT;
            else {
                System.out.println("Invalid input");
                continue;
            }
            String state = snake.changeDirection(direction);
            if (state.toLowerCase().equals("game_over")) {
                System.out.println("Game Over");
                return;
            }
        }
    }
}
