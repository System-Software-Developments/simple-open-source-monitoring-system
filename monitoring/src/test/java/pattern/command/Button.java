package pattern.command;

/**
 * Created by david100gom on 2018. 4. 15.
 *
 * Github : https://github.com/david100gom
 */
public class Button {

    private Command command;

    public Button(Command command) {
        setCommand(command);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressed() {
        command.execute();
    }
}
