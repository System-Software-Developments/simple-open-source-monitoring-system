package refactoring;

/**
 * Created by david100gom on 2018. 12. 23.
 *
 * Github : https://github.com/david100gom
 */
public class Main {

    public static void main(String[] args) {
        Parent pa = new Parent();

        pa.display();

        Child ch = new Child();

        ch.display();

        Parent pc = new Child();

        pc.display();

        //Child cp = (Child)new Parent();
        //cp.display();
    }

}