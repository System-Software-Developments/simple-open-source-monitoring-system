package pattern.singleton;

/**
 * Created by david100gom on 2018. 4. 1.
 *
 * Github : https://github.com/david100gom
 */
public class UserThread extends Thread{

    public UserThread(String name) {
        super(name);
    }

    public void run() {
        Printer2 printer = Printer2.getPrinter();
        printer.print(Thread.currentThread().getName() + " print2 using " +printer.toString() + ".");
    }
}
