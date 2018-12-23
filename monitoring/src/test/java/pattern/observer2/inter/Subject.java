package pattern.observer2.inter;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public interface Subject {

    public void add(Observer observer);

    public void remove(Observer observer);

    public void nodifyObservers();

    public void notifyObserver(Observer observer);
}
