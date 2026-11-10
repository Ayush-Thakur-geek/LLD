package projects.parkingLot;

public class Payment {
    private double amount;
    private PaymentStrategy paymentStrategy;
    public Payment(double amount, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
    }
    public void processPayment() throws InterruptedException {
        if (amount > 0) {
            paymentStrategy.processPayment(amount);  // Delegating to strategy
        } else {
            System.out.println("Invalid payment amount.");
        }
    }
}
