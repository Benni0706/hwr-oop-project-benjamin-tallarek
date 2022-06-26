package hwr.oop;

public interface SerialPort {

    SerialPort getCommPort(String comPort);

    void openPort();

    void writeBytes(byte[] buffer, int length);
}
