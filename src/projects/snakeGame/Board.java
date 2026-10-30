package projects.snakeGame;

import java.util.ArrayList;
import java.util.List;

public class Board implements Publisher {
    int[][] grid;
    public int r;
    public int c;
    private List<Subscriber> subscribers;
    Board(int r, int c) {
        this.grid = new int[r][c];
        this.r = r;
        this.c = c;
        this.grid[0][0] = 1;
        this.grid[0][1] = 1;
        this.subscribers = new ArrayList<Subscriber>();
    }
    public boolean isOccupied(Position position) {
        int x = position.getX();
        int y = position.getY();
        return grid[x][y] != 0;
    }

    public boolean placeFood(Position position) {
        int x = position.getX();
        int y = position.getY();
        if (isOccupied(position)) return false;
        grid[x][y] = 2;
        return true;
    }

    public String moveSnake(Position position, Position removed, GameStateContext context, Direction direction) {
        int x = position.getX();
        int y = position.getY();
        if (x < 0 || x >= this.r || y < 0 || y >= this.c || grid[x][y] == 1) {
            context.next();
            System.out.println("Snake died");
            return context.gameState.getState();
        } else if (grid[x][y] == 2) {
            System.out.println("snake ate the food");
            this.notifySubscribers(direction);
        }
        this.grid[x][y] = 1;
        if (removed != null) {
            int xr = removed.getX();
            int yr = removed.getY();
            this.grid[xr][yr] = 0;
        }
        System.out.println("snake at x: " +  x + " y: " + y);
        return context.gameState.getState();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Direction direction) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(direction);
        }
    }
}
