package projects.parkingLot;

public interface ParkingFeeStrategy {
    double calculateFee(String vehicleType, int duration, DurationType durationType);
}
