package behaviouralDesignPattern;

public class StrategyDesignPattern {
    public static void main(String[] args) {
        PayPal paypal = new PayPal();
        CoinBase coinBase = new CoinBase();
        PaymentProcessor paymentProcessor = new PaymentProcessor(paypal);
        paymentProcessor.processPayment();
        paymentProcessor.setPaymentStrategy(coinBase);
        paymentProcessor.processPayment();
    }
}

class PaymentProcessor {
    private PaymentStrategy paymentStrategy;
    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentStrategy setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        return paymentStrategy;
    }

    public void processPayment() {
        paymentStrategy.processPayment();
    }
}

interface PaymentStrategy {
    void processPayment();
}

class PayPal implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("PayPal payment is being processed...");
    }
}

class Paytm implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Paytm payment is being processed...");
    }
}

class CoinBase implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("CoinBase payment is being processed...");
    }
}
