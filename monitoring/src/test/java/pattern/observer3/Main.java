package pattern.observer3;

/**
 * Created by david100gom on 2018. 12. 22.
 *
 * Github : https://github.com/david100gom
 */
public class Main {

    public static void main(String [] args) {
        SensorData sensorData = new  SensorData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(sensorData);

        sensorData.setData(80, 65, 30.4f);
        sensorData.setData(82, 70, 29.2f);
        sensorData.setData(78, 90, 29.2f);
    }

}
