package hwr.oop;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.nio.charset.StandardCharsets;

public class MotionSensor implements Sensor {

    private SerialPort comPort;

    public MotionSensor(){
        this(SerialPort.getCommPort("COM5"));
    }

    public MotionSensor(SerialPort comPort){
        this.comPort = comPort;
        this.comPort.openPort();
        startListening();
    }

    @Override
    public void startListening(){
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
                System.out.println(input);
            }
        });
    }

    @Override
    public void sendString(String data){
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
