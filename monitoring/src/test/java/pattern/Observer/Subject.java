package pattern.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david100gom on 2018. 4. 22.
 *
 * Github : https://github.com/david100gom
 */
public abstract class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for(Observer o:observers) {
            o.update();
        }
    }

}