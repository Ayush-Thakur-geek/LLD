package projects.parkingLot;

public interface PaymentStrategy {
    public boolean processPayment(double amount) throws InterruptedException;
}
