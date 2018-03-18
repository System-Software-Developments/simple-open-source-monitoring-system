package pattern.strategy;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by david100gom on 2018. 3. 18.
 *
 * Github : https://github.com/david100gom
 */
@SpringBootTest
public class Client {

    @Test
    public void contextLoads() {

        Robot v = new TaekwonV("TaekwnV");
        System.out.println("31");
        Robot atom = new Atom("Atom");
        System.out.println("32");
        v.setMovingStrategy(new WalkingStrategy());
        v.setAttackStrategy(new MissleStrategy());

        atom.setMovingStrategy(new FlyingStrategy());
        atom.setAttackStrategy(new PunchStrategy());

        System.out.println(v.getName());
        v.move();
        v.attack();


        System.out.println(v.getName());
        atom.move();
        atom.attack();


    }
}
