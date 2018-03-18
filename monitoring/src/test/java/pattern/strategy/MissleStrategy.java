package pattern.strategy;

/**
 * Created by david100gom on 2018. 3. 18.
 *
 * Github : https://github.com/david100gom
 */
public class MissleStrategy implements AttackStrategy{
    @Override
    public void attack() {
        System.out.println("Missle");
    }
}
