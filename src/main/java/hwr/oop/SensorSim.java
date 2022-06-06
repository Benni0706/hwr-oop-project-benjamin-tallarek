package hwr.oop;

import java.util.Observable;

public class SensorSim extends Observable implements SensorInterface{

    @Override
    public void setup() {
        listener();
    }

    @Override
    public void listener() {

    }

    @Override
    public void activateSensor() {

    }

    @Override
    public void deactivateSensor() {

    }
}
