package refactoring.introduceNullObject;

/**
 * Created by david100gom on 2018. 12. 23.
 *
 * Github : https://github.com/david100gom
 */
public class NullLabel extends Label {

    public NullLabel() {
        super("(none)");
    }

    @Override
    public void display() {
    }

    @Override
    public boolean isNull() {
        return true;
    }
}

