package creationalDesignPattern;

public class FactoryDesignPattern {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory("car");
        Vehicle vehicle = vehicleFactory.getVehicle();
        vehicle.start();
    }
}

interface Vehicle {
    void start();
    void stop();
}

class CarVehicle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car start");
    }
    @Override
    public void stop() {
        System.out.println("Car stop");
    }
}

class BusVehicle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bus start");
    }
    @Override
    public void stop() {
        System.out.println("Bus stop");
    }
}

class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike start");
    }
    @Override
    public void stop() {
        System.out.println("Bike stop");
    }
}

class VehicleFactory {
    private static String vehicleType;
    public VehicleFactory(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public static Vehicle getVehicle() {
        if (vehicleType.equals("car")) return new CarVehicle();
        else if (vehicleType.equals("bus")) return new BusVehicle();
        else if (vehicleType.equals("bike")) return new Bike();
        else return null;
    }
}