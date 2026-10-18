package behaviouralDesignPattern.momentoPattern;

public class StateDP {
    public static void main(String[] args) {
        TrafficLightContext context = new TrafficLightContext();
        context.next(context);
    }
}

interface TrafficLightState {
    void next(TrafficLightContext context);
    String getColor();
}

class RedColorState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context) {
        context.setState(new GreenColorState());
        System.out.println("RedColorState next to Green");
    }

    @Override
    public String getColor() {
        return "red";
    }
}

class GreenColorState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context) {
        context.setState(new YellowColorState());
        System.out.println("GreenColorState next to Yellow");
    }

    @Override
    public String getColor() {
        return "green";
    }
}

class YellowColorState implements TrafficLightState {

    @Override
    public void next(TrafficLightContext context) {
        context.setState(new RedColorState());
    }

    @Override
    public String getColor() {
        return "yellow";
    }
}

class TrafficLightContext {
    TrafficLightState state;
    TrafficLightContext() {
        this.state = new RedColorState();
    }
    void setState(TrafficLightState state) {
        this.state = state;
    }
    void next(TrafficLightContext context) {
        state.next(context);
    }
}
