package projects.snakeGame;

public class FoodGenerator implements Subscriber{
    Board board;
    FoodGenerator(Board board){
        this.board = board;
        this.update(Direction.UP);
    }
    @Override
    public void update(Direction direction) {
        int r = this.board.r;
        int c = this.board.c;

        while (true) {
            int x = (int)(Math.random() * r);
            int y  = (int)(Math.random() * c);
            boolean placed = this.board.placeFood(new Position(x, y));
            if (placed) {
                System.out.println("food placed at x: " + x + " y: " + y);
                return;
            }
        }
    }
}
