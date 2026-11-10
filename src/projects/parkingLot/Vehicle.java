package projects.parkingLot;

public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType type;
    protected ParkingFeeStrategy parkingFeeStrategy;
    protected int[] ticket;
    Vehicle(VehicleType type, String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.parkingFeeStrategy = parkingFeeStrategy;
        this.ticket = null;
    }

    public abstract void acquireTicket(int[] ticket);
    public abstract int[] giveTicket();
    public abstract double calculateFee(int duration, DurationType durationType);
}
