package creationalDesignPattern;

public class BuilderDesignPattern {
    public static void main(String[] args) {
        Car.CarBuilder builder = new Car.CarBuilder();
        Car c1 = builder
                .color("matte black")
                .engine("v8")
                .seats(2)
                .sunroof(true)
                .build();

        Car.CarBuilder builder2 = new Car.CarBuilder();
        Car c2 = builder2
                .build();

        System.out.println(c2.toString());
    }
}

class Car {
    private String color;
    private int seats;
    private boolean sunroof;
    private String speakers;
    private String engine;
    private Car(CarBuilder builder) {
        this.color = builder.color;
        this.seats = builder.seats;
        this.sunroof = builder.sunroof;
        this.speakers = builder.speakers;
        this.engine = builder.engine;
    }

    public String getColor() {
        return color;
    }
    public int getSeats() {
        return seats;
    }
    public boolean isSunroof() {
        return sunroof;
    }
    public String getSpeakers() {
        return speakers;
    }
    public String getEngine() {
        return engine;
    }
    public String toString(){
        return "color: " + this.color +
                "\nseats: " + this.seats +
                "\nsun roof present: " + this.sunroof +
                "\nspeakers: " + this.speakers +
                "\nengine: " + this.engine;
    }

    public static class CarBuilder {
        private String color = "white";
        private int seats = 4;
        private boolean sunroof = false;
        private String speakers = "jbl";
        private String engine = "v6";
        public CarBuilder color(String color) {
            this.color = color;
            return this;
        }
        public CarBuilder seats(int seats) {
            this.seats = seats;
            return this;
        }
        public CarBuilder sunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }
        public CarBuilder speakers(String speakers) {
            this.speakers = speakers;
            return this;
        }
        public CarBuilder engine(String engine) {
            this.engine = engine;
            return this;
        }
        public Car build() {
            return new Car(this);
        }
    }
}