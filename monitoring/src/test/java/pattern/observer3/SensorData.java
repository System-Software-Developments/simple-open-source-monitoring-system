package pattern.observer3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class SensorData implements Subject {

    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float battery;

    public SensorData() {
        observers = new ArrayList<>();
    }

    @Override
    public void add(Observer o) {
        observers.add(o);
    }

    @Override
    public void remove(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer ob: observers) {
            ob.update(temperature, humidity, battery);
        }
    }

    public void dataChanged() {
        notifyObservers();
    }

    public void setData(float temperature, float humidity, float battery) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.battery = battery;
        dataChanged();

    }

}
