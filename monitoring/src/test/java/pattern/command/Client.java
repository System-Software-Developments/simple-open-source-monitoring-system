package pattern.command;

/**
 * Created by david100gom on 2018. 4. 15.
 *
 * Github : https://github.com/david100gom
 */
public class Client {

    public static void main(String[] args) {
        Lamp lamp = new Lamp();

        Command command1 = new LampOnCommand(lamp);
        Command command3 = new LampOffCommand(lamp);
        Button button = new Button(command1);
        button.pressed();


        Alarm alarm = new Alarm();
        Command command2 = new AlarmOnCommand(alarm);
        button = new Button(command2);
        button.pressed();

        button.setCommand(command1);
        button.pressed();

        button.setCommand(command3);
        button.pressed();

    }
}

