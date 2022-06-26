package hwr.oop;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;

public class SerialPortAdapter implements Port {

    private SerialPort serialPort;

    public SerialPortAdapter(SerialPort serialPort){
        this.serialPort = serialPort;
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
    public void addDataListener(SerialPortDataListener listener) {
        serialPort.addDataListener(listener);
    }
}
