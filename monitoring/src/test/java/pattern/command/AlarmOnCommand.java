package pattern.command;

/**
 * Created by david100gom on 2018. 4. 15.
 *
 * Github : https://github.com/david100gom
 */
public class AlarmOnCommand implements Command{

    private Alarm alarm;

    public AlarmOnCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.start();
    }
}