package hwr.oop;

public interface Sensor {

    void startListening();
    void activateSensor();
    void deactivateSensor();
    void sendString(String data);

}
