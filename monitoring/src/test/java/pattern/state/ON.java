package pattern.state;

/**
 * Created by david100gom on 2018. 4. 11.
 *
 * Github : https://github.com/david100gom
 */
public class ON implements State{

    private static ON on = new ON();
    private ON() {

    }

    public static ON getInstance() {
        return on;
    }

    public void onButtonPushed(Light light) {
        System.out.println("nothing...");
    }

    @Override
    public void offButtonPushed(Light light) {
        light.setState(OFF.getInstance());
        System.out.println("Light OFF...");
    }


}
