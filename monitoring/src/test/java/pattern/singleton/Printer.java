package pattern.singleton;

/**
 * Created by david100gom on 2018. 4. 1.
 *
 * Github : https://github.com/david100gom
 *
 * 정적 변수에 인스턴스를 만들어 바로 초기화하는 방법
 *
 */
public class Printer {

    private static Printer printer = new Printer();
    private int counter = 0;
    private Printer() {}

    public static Printer getPrinter() {
        return printer;
    }

    public void print(String str) {

        synchronized (this) {
            counter++;
            System.out.println(str + ":" + counter);
        }
    }

}
