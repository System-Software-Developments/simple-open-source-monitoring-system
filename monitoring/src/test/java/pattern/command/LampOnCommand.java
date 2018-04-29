package pattern.command;

/**
 * Created by david100gom on 2018. 4. 15.
 *
 * Github : https://github.com/david100gom
 */
public class LampOnCommand implements Command{

    private Lamp lamp;

    public LampOnCommand(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void execute() {
        lamp.turnOn();
    }

}