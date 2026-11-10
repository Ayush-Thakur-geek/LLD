package projects.parkingLot;

public class ParkingSlot {
    private VehicleType slotType;
    private boolean isOccupied;
    ParkingSlot(VehicleType slotType, boolean isOccupied) {
        this.slotType = slotType;
        this.isOccupied = isOccupied;
    }
    public void occupy() {
        this.isOccupied = true;
    }
    public void unoccupy() {
        this.isOccupied = false;
    }
}
