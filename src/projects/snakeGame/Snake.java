package projects.snakeGame;

import java.util.Deque;
import java.util.LinkedList;

public class Snake implements Subscriber {
    private Deque<Position> snake;
    private Board board;
    private GameStateContext gameStateContext;
    Snake(Board board, GameStateContext gameStateContext) {
        this.snake = new LinkedList<>();
        this.board = board;
        this.gameStateContext = gameStateContext;
        this.snake.addFirst(new Position(0,0));
        this.snake.addFirst(new Position(0,1));
    }
    @Override
    public void update(Direction direction) {
        Position head = snake.peek();
        int x = head.getX();
        int y = head.getY();
        int prevSize = this.snake.size();
        Position position;
        if (direction == Direction.UP && x - 1 >= 0) {
            position = new Position(x - 1, y);
            this.snake.addFirst(position);
            this.board.moveSnake(position, null, gameStateContext, direction);
        } else if (direction == Direction.DOWN && x + 1 < board.r) {
            position = new Position(x + 1, y);
            this.snake.addFirst(position);
            this.board.moveSnake(position, null, gameStateContext, direction);
        } else if (direction == Direction.LEFT && y - 1 >= 0) {
            position = new Position(x, y - 1);
            this.snake.addFirst(position);
            this.board.moveSnake(position, null, gameStateContext, direction);
        } else if (direction == Direction.RIGHT && y + 1 < board.c) {
            position = new Position(x, y + 1);
            this.snake.addFirst(position);
            this.board.moveSnake(position, null, gameStateContext, direction);
        } else {
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!this.board.isOccupied(new Position(nx, ny))) {
                    position = new Position(nx, ny);
                    this.snake.addFirst(position);
                    this.board.moveSnake(position, null, gameStateContext, direction);
                }
            }
        }
        System.out.println("Snake length increased from: " + prevSize + " to: " + this.snake.size());
    }

    public String changeDirection(Direction direction) {
        Position head = snake.peek();
        int x = head.getX();
        int y = head.getY();
        Position removed = this.snake.pollLast();
        System.out.println("removed tail x: " + removed.getX() + " y: " + removed.getY());
        Position newPosition;
        if (direction == Direction.UP) {
            newPosition = new Position(x - 1, y);
            this.snake.addFirst(newPosition);
            return board.moveSnake(newPosition, removed, gameStateContext, direction);
        }
        else if (direction ==  Direction.DOWN) {
            newPosition = new Position(x + 1, y);
            this.snake.addFirst(newPosition);
            return board.moveSnake(newPosition, removed, gameStateContext, direction);
        }
        else if (direction == Direction.LEFT) {
            newPosition = new Position(x, y - 1);
            this.snake.addFirst(newPosition);
            return board.moveSnake(newPosition, removed, gameStateContext, direction);
        }
        else {
            newPosition = new Position(x, y + 1);
            this.snake.addFirst(newPosition);
            return board.moveSnake(newPosition, removed, gameStateContext, direction);
        }
    }
}
