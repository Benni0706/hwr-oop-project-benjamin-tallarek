package hwr.oop.alarmSystem;

import hwr.oop.serialCommunication.SerialPortAdapter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ManualTest {

    @Test
    @Disabled
    void manualTest_Simulated(){
        SerialPort serialPort = new SimulatedSerialPort();

        Sensor sensor = new MotionSensor(serialPort);
        Monitoring monitoring = new Monitoring(sensor);
        sensor.attach(monitoring);
        serialPort.attach((PortObserver) sensor);
        boolean running = true;
        while(running) {
            running = monitoring.checkInput();
        }
    }

    @Test
    @Disabled
    void manualTest_Real(){
        SerialPort serialPort = new SerialPortAdapter(com.fazecast.jSerialComm.SerialPort.getCommPort("COM5"));

        Sensor sensor = new MotionSensor(serialPort);
        Monitoring monitoring = new Monitoring(sensor);
        sensor.attach(monitoring);
        serialPort.attach((PortObserver) sensor);
        boolean running = true;
        while(running) {
            running = monitoring.checkInput();
        }
    }
}
