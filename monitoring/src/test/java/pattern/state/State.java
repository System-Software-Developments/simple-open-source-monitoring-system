package pattern.state;

/**
 * Created by david100gom on 2018. 4. 11.
 *
 * Github : https://github.com/david100gom
 */
public interface State {

    public void onButtonPushed(Light light);
    public void offButtonPushed(Light light);

}
