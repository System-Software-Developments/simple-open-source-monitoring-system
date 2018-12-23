package pattern.observer3;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement{

    private float temperature;
    private float humidity;
    private float battery;
    private Subject subject;


    public CurrentConditionsDisplay(Subject subject) {
        this.subject = subject;
        subject.add(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions : " + temperature + "F degrees and " + humidity + "% humidity" + " battery :" +battery);
    }

    @Override
    public void update(float temperature, float humidity, float battery) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.battery = battery;
        display();
    }


}
