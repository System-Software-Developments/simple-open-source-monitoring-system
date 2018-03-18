package pattern.strategy;

/**
 * Created by david100gom on 2018. 3. 18.
 *
 * Github : https://github.com/david100gom
 */
public class PunchStrategy implements AttackStrategy {
    @Override
    public void attack() {
        System.out.print("Punch");
    }
}
