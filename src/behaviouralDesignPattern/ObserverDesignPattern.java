package behaviouralDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverDesignPattern {
    public static void main(String[] args) {
        YoutubeChannelImpl ytc = new YoutubeChannelImpl("xyz");
        YoutubeSubscriber alice = new YoutubeSubscriber("alice");
        EmailSubscriber bob = new EmailSubscriber("bob");
        ytc.addSubscriber(alice);
        ytc.addSubscriber(bob);
        ytc.addVideo("hello world");
    }
}

interface Subscriber {
    public void update(String video);
}

interface YoutubeChannel {
    public void addSubscriber(Subscriber subscriber);
    public void removeSubscriber(Subscriber subscriber);
    public void notifySubscribers();
}

class YoutubeChannelImpl implements YoutubeChannel {

    List<Subscriber> subscribers = new ArrayList<Subscriber>();
    String video;
    String channelName;
    public YoutubeChannelImpl(String channelName) {
        this.channelName = channelName;
    }
    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this.video);
        }
    }

    public void addVideo(String video) {
        this.video = video;
        notifySubscribers();
    }
}

class YoutubeSubscriber implements Subscriber {
    String name;
    YoutubeSubscriber(String name) {
        this.name = name;
    }
    @Override
    public void update(String video) {
        System.out.println("The Youtube subscriber " + name + " received video: " + video + " update");
    }
}

class EmailSubscriber implements Subscriber {
    String name;
    EmailSubscriber(String name) {
        this.name = name;
    }
    @Override
    public void update(String video) {
        System.out.println("The Email subscriber " + name + " received video: " + video + " update");
    }
}