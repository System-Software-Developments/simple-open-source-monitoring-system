package pattern.singleton;

/**
 * Created by david100gom on 2018. 4. 1.
 *
 * Github : https://github.com/david100gom
 */
public class Printer2 {

    private static Printer2 printer = null;
    private int counter = 0;
    private Printer2() {

    }

    public synchronized static Printer2 getPrinter() {
        if(printer == null)
                printer = new Printer2();

        return printer;
    }

    public void print(String str) {
        synchronized (this) {
            counter++;
            System.out.println(str+":"+counter);
        }
    }

}