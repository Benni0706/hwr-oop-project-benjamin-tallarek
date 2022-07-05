package hwr.oop.alarmSystem;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        SerialPort serialPort = new SimulatedSerialPort();
        //SerialPort serialPort = new SerialPortAdapter(com.fazecast.jSerialComm.SerialPort.getCommPort("COM5"));

        Sensor sensor = new MotionSensor(serialPort);
        Monitoring monitoring = new Monitoring(sensor);
        sensor.attach(monitoring);
        serialPort.attach((PortObserver) sensor);
        monitoring.startMonitoring();
    }
}
