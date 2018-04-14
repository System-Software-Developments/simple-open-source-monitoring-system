package pattern.state;

/**
 * Created by david100gom on 2018. 4. 11.
 *
 * Github : https://github.com/david100gom
 */
public class Client {

    public static void main(String[] args) {
        Light light = new Light();

        light.offButtonPushed();
        light.onButtonPushed();
        light.offButtonPushed();
    }
}
