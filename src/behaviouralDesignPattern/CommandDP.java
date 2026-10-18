package behaviouralDesignPattern;

public class CommandDP {
    public static void main(String[] args) {
        TV tv = new TV();
        Command turnOn = new TurnOnCommand(tv);
        Command turnOff = new TurnOffCommand(tv);
        Remote remote = new Remote();
        remote.setOnCommand(turnOn);
        remote.pressOnButton();
        Command changeChannel = new ChangeChannelCommand(tv, 20);
        changeChannel.execute();
    }
}
interface Command {
    public void execute();
}

class TV {
    private int vol;
    private int currentChannel;
    public TV() {
        this.vol = 20;
        this.currentChannel = 1;
    }
    public void turnOn() {
        System.out.println("TV on");
    }
    public void turnOff() {
        System.out.println("TV off");
    }
    public void incVolume() {
        System.out.println("Volume increased to: " + ++vol);
    }
    public void decVolume() {
        System.out.println("Volume decreased to: " + --vol);
    }
    public void changeChannel(int channel) {
        this.currentChannel = channel;
        System.out.println("Channel changed to: " + channel);
    }
}

class Remote {
    private Command turnOn;
    private Command turnOff;
    public void setOnCommand(Command turnOn) {
        this.turnOn = turnOn;
    }
    public void setOffCommand(Command turnOff) {
        this.turnOff = turnOff;
    }
    public void pressOnButton() {
        turnOn.execute();
    }
    public void pressOffButton() {
        turnOff.execute();
    }
}

class TurnOnCommand implements Command {
    private final TV tv;
    TurnOnCommand(TV tv) {
        this.tv = tv;
    }
    @Override
    public void execute() {
        tv.turnOn();
    }
}

class TurnOffCommand implements Command {
    private final TV tv;
    TurnOffCommand(TV tv) {
        this.tv = tv;
    }
    @Override
    public void execute() {
        tv.turnOff();
    }
}

class ChangeChannelCommand implements Command {
    private TV tv;
    private int channel;
    public ChangeChannelCommand(TV tv, int channel) {
        this.tv = tv;
        this.channel = channel;
    }
    @Override
    public void execute() {
        tv.changeChannel(channel);
    }
}

class IncreaseVolumeCommand implements Command {
    private final TV tv;
    public IncreaseVolumeCommand(TV tv) {
        this.tv = tv;
    }
    @Override
    public void execute() {
        tv.incVolume();
    }
}

class DecreaseVolumeCommand implements Command {
    private final TV tv;
    public DecreaseVolumeCommand(TV tv) {
        this.tv = tv;
    }
    @Override
    public void execute() {
        tv.decVolume();
    }
}
