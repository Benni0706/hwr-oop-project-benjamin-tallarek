package hwr.oop.serialCommunication;

import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import hwr.oop.alarmSystem.PortObserver;
import hwr.oop.alarmSystem.SerialPort;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SerialPortAdapter implements SerialPort {

    private List<PortObserver> observer = new ArrayList<>();
    private com.fazecast.jSerialComm.SerialPort serialPort;

    public SerialPortAdapter(com.fazecast.jSerialComm.SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public List<PortObserver> getObserver() {
        return observer;
    }

    @Override
    public void openPort() {
        serialPort.openPort();
    }

    @Override
    public void writeBytes(byte[] buffer, int length) {
        serialPort.writeBytes(buffer, length);
    }

    @Override
    public void startListening() {
        serialPort.addDataListener(new SerialPortDataListener() {

            @Override
            public int getListeningEvents() {
                return com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent event)
            {
                byte[] newData = event.getReceivedData();
                String input = new String(newData, StandardCharsets.UTF_8);
                setMessage(input);
            }
        });
    }

    @Override
    public void attach(PortObserver observer) {
        this.observer.add(observer);
    }

    @Override
    public void detach(PortObserver observer) {
        this.observer.remove(observer);
    }

    @Override
    public void setMessage(String message) {
        for( PortObserver observer : this.observer){
            observer.update(message);
        }
    }
}
