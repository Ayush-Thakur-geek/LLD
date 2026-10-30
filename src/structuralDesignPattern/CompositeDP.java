package structuralDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class CompositeDP {
    public static void main(String[] args) {
        SmartComponent ac = new Ac();
        SmartComponent light = new Light();
        // Create a room and add devices
        CompositeSmartComponent room1 = new CompositeSmartComponent();
        room1.addSmartComponent(ac);
        room1.addSmartComponent(light);
        // Add more rooms for demonstration
        CompositeSmartComponent room2 = new CompositeSmartComponent();
        room2.addSmartComponent(new Ac());
        room2.addSmartComponent(new Light());
        // Create a floor and add rooms
        CompositeSmartComponent floor = new CompositeSmartComponent();
        floor.addSmartComponent(room1);
        floor.addSmartComponent(room2);
        // Create the house and add floors
        CompositeSmartComponent house = new CompositeSmartComponent();
        house.addSmartComponent(floor);
        // Control the entire house
        System.out.println("Turning ON all devices in the house:");
        house.turnOn();
        System.out.println("Turning OFF all devices in the house:");
        house.turnOff();
        // Control a single floor
        System.out.println("Turning ON all devices on the first floor:");
        floor.turnOn();
        System.out.println("Turning OFF all devices on the first floor:");
        floor.turnOff();
        // Control a single room
        System.out.println("Turning ON all devices in Room 1:");
        room1.turnOn();
        System.out.println("Turning OFF all devices in Room 1:");
        room1.turnOff();

    }
}

interface SmartComponent {
    public void turnOn();
    public void turnOff();
}

class CompositeSmartComponent implements SmartComponent {

    List<SmartComponent> smartComponents = new ArrayList<SmartComponent>();
    public void addSmartComponent(SmartComponent smartComponent) {
        smartComponents.add(smartComponent);
    }
    public void removeSmartComponent(SmartComponent smartComponent) {
        smartComponents.remove(smartComponent);
    }
    @Override
    public void turnOn() {
        for (SmartComponent smartComponent : this.smartComponents) {
            smartComponent.turnOn();
        }
    }

    @Override
    public void turnOff() {
        for (SmartComponent smartComponent : this.smartComponents) {
            smartComponent.turnOff();
        }
    }
}

class Light implements SmartComponent {

    @Override
    public void turnOn() {
        System.out.println("Light is on");
    }

    @Override
    public void turnOff() {
        System.out.println("Light is off");
    }
}

class Ac implements SmartComponent {

    @Override
    public void turnOn() {
        System.out.println("AC is on");
    }

    @Override
    public void turnOff() {
        System.out.println("AC is off");
    }
}