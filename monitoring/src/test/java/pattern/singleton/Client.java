package pattern.singleton;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by david100gom on 2018. 4. 1.
 *
 * Github : https://github.com/david100gom
 */
@SpringBootTest
public class Client {

    @Test
    public void contextLoads() {

        UserThread[] user = new UserThread[5];
        for(int i = 0; i < 5; i++) {
            user[i] = new UserThread((i+1)+"-Thread");
            user[i].start();
        }

    }
}
