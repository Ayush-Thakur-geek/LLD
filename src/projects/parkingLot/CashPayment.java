package projects.parkingLot;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) throws InterruptedException {
        System.out.println("You pay $" + amount);
        Thread.sleep(1000);
        System.out.println("Payment completed");
        return true;
    }
}
