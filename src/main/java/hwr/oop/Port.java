package hwr.oop;

import com.fazecast.jSerialComm.SerialPortDataListener;

public interface Port {

    int LISTENING_EVENT_DATA_RECEIVED = 0;

    void openPort();

    void writeBytes(byte[] buffer, int length);

    void addDataListener(SerialPortDataListener listener);
}
