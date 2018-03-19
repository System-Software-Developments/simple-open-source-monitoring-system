package socket;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by david100gom on 2018. 3. 17.
 *
 * Github : https://github.com/david100gom
 */
@SpringBootTest
public class SocketTest {

    @Test
    public void contextLoads() {

        try {
            String str = "123456";

            // 한 글자씩 헥사(Hex)로 출력
            System.out.println(stringToHex(str));
            // 출력 결과: 41 70 70 6C 65

        }catch (Exception e) {

        }
    }

    // 문자열을 헥사 스트링으로 변환하는 메서드
    public static String stringToHex(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            result += String.format("%02X ", (int) s.charAt(i));
        }

        return result;
    }
}
