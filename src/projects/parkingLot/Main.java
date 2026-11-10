package projects.parkingLot;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingFeeStrategy parkingFeeStrategyBasic = new BasicHourlyRateStrategy();

        ParkingLot lot = ParkingLot.buildParkingLot(2, 8, 10, 20);
        Vehicle car = new Car("CH01QC1423", parkingFeeStrategyBasic);
        int[] ticket = ParkingLot.allocateSlots(car);
        car.acquireTicket(ticket);
        double amount = car.calculateFee(2, DurationType.HOURS);
        PaymentStrategy card = new CardPayment();
        card.processPayment(amount);
        ParkingLot.deallocateSlots(car.giveTicket(), car);
    }
}
