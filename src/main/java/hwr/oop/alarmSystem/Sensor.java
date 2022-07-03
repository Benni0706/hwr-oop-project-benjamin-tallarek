package hwr.oop.alarmSystem;

import java.util.List;

interface Sensor {

    void startListening();
    void activateSensor();
    void deactivateSensor();
    List<SensorObserver> getObserver();
    void sendString(String data);
    void attach(SensorObserver observer);
    void detach(SensorObserver observer);
}
