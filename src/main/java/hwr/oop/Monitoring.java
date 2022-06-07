package hwr.oop;

import java.io.IOException;

public class Monitoring{

    private Sensor sensor;

    public Monitoring(Sensor sensor){
        this.sensor = sensor;
    }

    public static void main(String[] args) throws IOException {
        Sensor sensor = new SensorSim();
        Monitoring monitoring = new Monitoring(sensor);
    }
}
