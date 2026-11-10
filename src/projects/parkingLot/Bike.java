package projects.parkingLot;

public class Bike extends Vehicle {
    Bike(String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        super(VehicleType.BIKE, licensePlate, parkingFeeStrategy);
    }

    @Override
    public void acquireTicket(int[] ticket) {
        this.ticket = ticket;
    }

    @Override
    public int[] giveTicket() {
        return ticket;
    }

    @Override
    public double calculateFee(int duration, DurationType durationType) {
        return this.parkingFeeStrategy.calculateFee(this.type.toString(), duration, durationType);
    }
}
