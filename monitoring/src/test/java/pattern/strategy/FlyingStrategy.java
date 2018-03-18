package pattern.strategy;

/**
 * Created by david100gom on 2018. 3. 18.
 *
 * Github : https://github.com/david100gom
 */
public class FlyingStrategy implements MovingStrategy{

    @Override
    public void move() {
        System.out.println("I can fly.");
    }
}
