package pattern.observer3;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public interface Subject {

    public void add(Observer o);
    public void remove(Observer o);
    public void notifyObservers();

}
