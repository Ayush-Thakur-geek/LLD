package projects.parkingLot;

public class CardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) throws InterruptedException {
        System.out.println("You pay $" + amount);
        Thread.sleep(100);
        System.out.println("Processing payment....");
        Thread.sleep(1000);
        System.out.println("Payment Complete");
        return true;
    }
}
