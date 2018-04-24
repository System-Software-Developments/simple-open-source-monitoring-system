package pattern.state;

/**
 * Created by david100gom on 2018. 4. 11.
 *
 * Github : https://github.com/david100gom
 */
public class OFF implements State {

    private static OFF off = new OFF();
    private OFF() {

    }

    public static OFF getInstance() {
        return off;
    }


    @Override
    public void onButtonPushed(Light light) {
        light.setState(ON.getInstance());
        System.out.println("Light ON...");
    }

    @Override
    public void offButtonPushed(Light light) {
        System.out.println("nothing...");
    }
}
