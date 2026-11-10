package projects.parkingLot;

import java.util.*;

public class ParkingLot {
    private static List<List<ParkingSlot>> carSlots;
    private static List<List<ParkingSlot>> bikeSlots;
    private static List<List<ParkingSlot>> truckSlots;
    private static int floors = 0;
    private static Map<Integer, Stack<Integer>> availableCarSlots;
    private static Map<Integer, Stack<Integer>> availableBikeSlots;
    private static Map<Integer, Stack<Integer>> availableTruckSlots;
    private static ParkingLot instance = null;
    private ParkingLot(int floors, int trucks, int cars, int bikes) {
        this.carSlots = new ArrayList<>();
        this.bikeSlots = new ArrayList<>();
        this.truckSlots = new ArrayList<>();
        this.availableCarSlots = new HashMap<>();
        this.availableBikeSlots = new HashMap<>();
        this.availableTruckSlots = new HashMap<>();
        this.floors = floors;
        for (int i = 0; i < floors; i++) {
            List<ParkingSlot> floorTruck = new ArrayList<>();
            Stack<Integer> truck = new Stack<>();
            for (int j = 0; j < trucks; j++) {
                floorTruck.add(new ParkingSlot(VehicleType.TRUCK, false));
                truck.add(j);
            }
            this.availableTruckSlots.put(i, truck);
            truckSlots.add(floorTruck);
            List<ParkingSlot> floorCar = new ArrayList<>();
            Stack<Integer> car = new Stack<>();
            for (int j = 0; j < cars; j++) {
                floorCar.add(new ParkingSlot(VehicleType.CAR, false));
                car.add(j);
            }
            this.availableCarSlots.put(i, car);
            carSlots.add(floorCar);
            List<ParkingSlot> floorBike = new ArrayList<>();
            Stack<Integer> bike = new Stack<>();
            for (int j = 0; j < bikes; j++) {
                floorBike.add(new ParkingSlot(VehicleType.BIKE, false));
                bike.add(j);
            }
            this.availableBikeSlots.put(i, bike);
            bikeSlots.add(floorBike);
        }
    }

    public static ParkingLot buildParkingLot(int floors, int trucks, int cars, int bikes) {
        if (instance == null) {
            instance = new ParkingLot(floors, trucks, cars, bikes);
            return instance;
        }
        return instance;
    }

    public static int[] allocateSlots(Vehicle vehicle) {
        if (instance == null) {
            System.out.println("Parking lot not initialized");
            return null;
        }
        boolean occupied = false;
        int x = 0;
        int y = 0;
        if (vehicle.type == VehicleType.CAR) {
            for (int i = 0; i < floors; i++) {
                Stack<Integer> carSlotStack = availableCarSlots.get(i);
                if (carSlotStack.isEmpty()) {
                    System.out.println(i + 1 + " floor is full, moving to next");
                    continue;
                }
                int j = carSlotStack.pop();
                ParkingSlot parkingSlot = carSlots.get(i).get(j);
                parkingSlot.occupy();
                occupied = true;
                x = i;
                y = j;
                System.out.println("Allocated slot " + x + " " + y);
                break;
            }
        } else if  (vehicle.type == VehicleType.BIKE) {
            for (int i = 0; i < floors; i++) {
                Stack<Integer> bikeSlotStack = availableBikeSlots.get(i);
                if (bikeSlotStack.isEmpty()) {
                    System.out.println(i + 1 + " floor is full, moving to next");
                    continue;
                }
                int j = bikeSlotStack.pop();
                ParkingSlot parkingSlot = bikeSlots.get(i).get(j);
                parkingSlot.occupy();
                occupied = true;
                x = i;
                y = j;
                System.out.println("Allocated slot " + x + " " + y);
                break;
            }
        } else if (vehicle.type == VehicleType.TRUCK) {
            for (int i = 0; i < floors; i++) {
                Stack<Integer> truckSlotStack = availableTruckSlots.get(i);
                if (truckSlotStack.isEmpty()) {
                    System.out.println(i + 1 + " floor is full, moving to next");
                    continue;
                }
                int j = truckSlotStack.pop();
                ParkingSlot parkingSlot = truckSlots.get(i).get(j);
                parkingSlot.occupy();
                occupied = true;
                x = i;
                y = j;
                System.out.println("Allocated slot " + x + " " + y);
                break;
            }
        }
        if (occupied) {
            System.out.println("lot occupied by: " + vehicle.licensePlate);
            return new int[]{x, y};
        } else {
            System.out.println("Parking lot is full");
            return null;
        }
    }

    public static void deallocateSlots(int[] ticket, Vehicle vehicle) {
        if (instance == null) {
            System.out.println("Parking lot not initialized");
        }
        if (vehicle.type == VehicleType.CAR) {
            Stack<Integer> carSlotStack = availableCarSlots.get(ticket[0]);
            carSlotStack.push(ticket[1]);
            ParkingSlot parkingSlot = carSlots.get(ticket[0]).get(ticket[1]);
            parkingSlot.unoccupy();
            System.out.println("Parking slot unoccupied for car " + vehicle.licensePlate);
        } else if (vehicle.type == VehicleType.BIKE) {
            Stack<Integer> bikeSlotStack = availableBikeSlots.get(ticket[0]);
            bikeSlotStack.push(ticket[1]);
            ParkingSlot parkingSlot = bikeSlots.get(ticket[0]).get(ticket[1]);
            parkingSlot.unoccupy();
            System.out.println("Parking slot unoccupied for bike " + vehicle.licensePlate);
        } else {
            Stack<Integer> truckSlotStack = availableTruckSlots.get(ticket[0]);
            truckSlotStack.push(ticket[1]);
            ParkingSlot parkingSlot = truckSlots.get(ticket[0]).get(ticket[1]);
            parkingSlot.unoccupy();
            System.out.println("Parking slot unoccupied for truck " + vehicle.licensePlate);
        }
    }
}
