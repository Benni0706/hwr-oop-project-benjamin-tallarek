package hwr.oop;

import com.fazecast.jSerialComm.SerialPort;

public class PortAdapter implements Port {

    private SerialPort serialPort;

    public PortAdapter(SerialPort serialPort){
        this.serialPort = serialPort;
    }

    @Override
    public Port getCommPort(String comPortDescription) {
        return port.getCommPort(comPortDescription);
    }

    @Override
    public void openPort() {
        port.openPort();
    }

    @Override
    public void writeBytes(byte[] buffer, int length) {
        port.writeBytes(buffer, length);
    }
}
