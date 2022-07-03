package hwr.oop.alarmSystem;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MotionSensor implements Sensor, PortObserver {

    private List<SensorObserver> observer = new ArrayList<>();
    private SerialPort serialPort;

    MotionSensor(SerialPort serialPort){
        this.serialPort = serialPort;
        this.serialPort.openPort();
    }

    @Override
    public void startListening(){
        serialPort.startListening();
    }

    @Override
    public List<SensorObserver> getObserver() {
        return observer;
    }

    @Override
    public void sendString(String data){
        byte[] buffer = new byte[data.length()];
        buffer = data.getBytes(StandardCharsets.UTF_8);
        serialPort.writeBytes(buffer, buffer.length);
    }

    @Override
    public void activateSensor() {
        sendString("activate");
    }

    @Override
    public void deactivateSensor() {
        sendString("deactivate");
    }

    @Override
    public void attach(SensorObserver observer) {
        this.observer.add(observer);
    }

    @Override
    public void detach(SensorObserver observer) {
        this.observer.remove(observer);
    }

    @Override
    public void update(String message) {
        for( SensorObserver observer : this.observer){
            observer.update(message);
        }
    }
}
