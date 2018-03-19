package pattern.dip;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by david100gom on 2018. 3. 17.
 *
 * Github : https://github.com/david100gom
 */
@SpringBootTest
public class DipTest {

    @Test
    public void contextLoads() {
        Toy toy = new Robot();
        Kid kid = new Kid();

        kid.setToy(toy);
        kid.play();

        Toy toy1 = new Lego();
        kid.setToy(toy1);
        kid.play();
    }

}