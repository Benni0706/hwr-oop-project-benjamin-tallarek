package hwr.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

public class Monitoring implements Observer {

    private SensorInterface sensor;

    public static void main(String[] args) throws IOException {
        Monitoring monitoring = new Monitoring();
        monitoring.setup(true);
    }

    void setup(boolean sim) {
        if(sim == true) {
            sensor = new SensorSim();
        } else if(sim == false) {
            sensor = new Sensor();
        }
    }

    @Override
    public void update(Observable o, Object input) {
        System.out.println((String) input);
    }
}
