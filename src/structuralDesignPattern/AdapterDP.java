package structuralDesignPattern;

public class AdapterDP {
    public static void main(String[] args) {
        AC ac = new AC();
        ACAdapter acAdapter = new ACAdapter(ac);
        acAdapter.turnOn();
        SmartLight smartLight = new SmartLight();
        SmartLightAdapter smartLightAdapter = new SmartLightAdapter(smartLight);
        smartLightAdapter.turnOn();
    }
}

interface SmartDevice {
    public void turnOn();
    public void turnOff();
}

class AC {
    // Method to connect to the Air Conditioner via Bluetooth
    public void connectViaBluetooth() {
        System.out.println("Air Conditioner connected via Bluetooth.");
    }

    // Method to start the cooling process
    public void startCooling() {
        System.out.println("Air Conditioner is now cooling.");
    }

    // Method to stop the cooling process
    public void stopCooling() {
        System.out.println("Air Conditioner stopped cooling.");
    }

    // Method to disconnect Bluetooth connection
    public void disconnectBluetooth() {
        System.out.println("Air Conditioner disconnected from Bluetooth.");
    }
}

class SmartLight {
    // Method to connect the Smart Light to Wi-Fi
    public void connectToWiFi() {
        System.out.println("Smart Light connected to Wi-Fi.");
    }

    // Method to turn the Smart Light on
    public void switchOn() {
        System.out.println("Smart Light is now ON.");
    }

    // Method to turn the Smart Light off
    public void switchOff() {
        System.out.println("Smart Light is now OFF.");
    }

    // Method to disconnect Wi-Fi connection
    public void disconnectWiFi() {
        System.out.println("Smart Light disconnected from Wi-Fi.");
    }
}

class ACAdapter implements SmartDevice {

    private final AC ac;
    ACAdapter(AC ac) {
        this.ac = ac;
    }

    @Override
    public void turnOn() {
        ac.connectViaBluetooth();
        ac.startCooling();
    }

    @Override
    public void turnOff() {
        ac.stopCooling();
        ac.disconnectBluetooth();
    }
}

// Adapter for Smart Light
class SmartLightAdapter implements SmartDevice {
    private SmartLight smartLight;
    public SmartLightAdapter(SmartLight smartLight) {
        this.smartLight = smartLight;
    }

    @Override
    public void turnOn() {
        smartLight.connectToWiFi();
        smartLight.switchOn();
    }

    @Override
    public void turnOff() {
        smartLight.switchOff();
        smartLight.disconnectWiFi();
    }
}
