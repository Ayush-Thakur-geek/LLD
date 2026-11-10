package projects.parkingLot;

public class Car extends Vehicle {
    Car(String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        super(VehicleType.CAR, licensePlate, parkingFeeStrategy);
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
