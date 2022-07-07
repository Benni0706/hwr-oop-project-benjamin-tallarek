package hwr.oop.alarmSystem;

import java.util.List;

public interface SerialPort {

    List<PortObserver> getObserver();
    void openPort();
    void writeBytes(byte[] buffer, int length);
    void startListening();
    void attach(PortObserver observer);
    void detach(PortObserver observer);
    void setMessage(String message);
}
