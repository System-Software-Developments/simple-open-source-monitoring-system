package refactoring.introduceNullObject;

/**
 * Created by david100gom on 2018. 12. 23.
 *
 * Github : https://github.com/david100gom
 */
public class Label {
    private final String _label;
    public Label(String label) {
        _label = label;
    }
    public void display() {
        System.out.println("display: " + _label);
    }
    public String toString() {
        return "\"" + _label + "\"";
    }
    public boolean isNull() {
        return false;
    }
}

