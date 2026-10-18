package behaviouralDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class MediatorDP {
    public static void main(String[] args) {
        Mediator mediator = new AuctionHouse();
        Bidder bidder = new Bidder("billu", 1000, mediator);
        Bidder bidder2 = new Bidder("ramu", 1000, mediator);
        Bidder bidder3 = new Bidder("juan", 1000, mediator);
        mediator.registerBidder(bidder);
        mediator.registerBidder(bidder2);
        mediator.registerBidder(bidder3);
        bidder.placebid(10000);
    }
}

interface Mediator {
    void registerBidder(Bidder bidder);
    void placeBid(Bidder bidder, int amount);
}

class AuctionHouse implements Mediator {
    private List<Bidder> bidders;

    AuctionHouse() {
        bidders = new ArrayList<>();
    }

    @Override
    public void registerBidder(Bidder bidder) {
        bidders.add(bidder);
        System.out.println("Bidder registered: " + bidder.name);
    }

    @Override
    public void placeBid(Bidder bidder, int amount) {
        System.out.println("Bidder: " + bidder.name + " placed bid: " + amount);
        for (Bidder b : bidders) {
            b.receiveBid(bidder, amount);
        }
    }
}

class Bidder {
    public String name;
    private int ongoingBid;
    private int lastPlacedBid;
    private Mediator mediator;
    public Bidder(String name, int ongoingBid, Mediator mediator) {
        this.name = name;
        this.lastPlacedBid = 0;
        this.ongoingBid = ongoingBid;
        this.mediator = mediator;
    }
    public void placebid(int amount) {
        this.lastPlacedBid = amount;
        mediator.placeBid(this, amount);
    }
    public void receiveBid(Bidder bidder, int amount) {
        this.ongoingBid = amount;
        System.out.println("Bidder: " + this.name + " received bid: " + amount + " placed by: " + bidder.name);
    }
}
