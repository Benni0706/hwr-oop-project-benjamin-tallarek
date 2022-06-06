package hwr.oop;

import com.fazecast.jSerialComm.*;

import javax.print.DocFlavor;
import java.nio.charset.StandardCharsets;
import java.util.Observable;

public class Sensor extends Observable implements SensorInterface{

    private SerialPort comPort;

    public void setup(){
        comPort = SerialPort.getCommPort("COM5");
        comPort.openPort();
        listener();
    }

    @Override
    public void listener(){
        comPort.addDataListener(new SerialPortDataListener() {

            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent event)
            {
                byte[] newData = event.getReceivedData();
                String input = new String(newData, StandardCharsets.UTF_8);
                setChanged();
                notifyObservers(input);
            }
        });
    }

    void sendString(String data){
        byte[] buffer = new byte[data.length()];
        for(int i = 0; i < data.length() - 1; i++){
            buffer[i] = Byte.parseByte(data.substring(i, i + 1));
        }
        comPort.writeBytes(buffer, buffer.length);
    }

    @Override
    public void activateSensor() {
        sendString("activate");
    }

    @Override
    public void deactivateSensor() {
        sendString("deactivate");
    }
}
