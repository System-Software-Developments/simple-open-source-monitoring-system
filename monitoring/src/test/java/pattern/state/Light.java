package pattern.state;

/**
 * Created by david100gom on 2018. 4. 11.
 *
 * Github : https://github.com/david100gom
 */
public class Light {

    private State state;

    public Light() {
        state = OFF.getInstance();
    }

    public void setState(State state) {
        this.state = state;
    }


    public void onButtonPushed() {
        state.onButtonPushed(this);
    }

    public void offButtonPushed() {
        state.offButtonPushed(this);
    }
}
