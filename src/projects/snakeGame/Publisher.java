package projects.snakeGame;


public interface Publisher {
    public void addSubscriber(Subscriber subscriber);
    public void removeSubscriber(Subscriber subscriber);
    public void notifySubscribers(Direction direction);
}
