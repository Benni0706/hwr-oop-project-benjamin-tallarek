package hwr.oop;

import com.fazecast.jSerialComm.SerialPortDataListener;

public class SimulatedPort implements Port {

    @Override
    public void openPort() {
        System.out.println("Opened Port");
    }

    @Override
    public void writeBytes(byte[] buffer, int length) {
        System.out.println("Wrote Bytes: " + buffer);
    }

    @Override
    public void addDataListener(SerialPortDataListener listener) {
        System.out.println("DataListener geaddet");
    }
}
