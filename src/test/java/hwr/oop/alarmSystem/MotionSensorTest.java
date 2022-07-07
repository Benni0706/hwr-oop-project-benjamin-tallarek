package hwr.oop.alarmSystem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MotionSensorTest {
    Sensor sensor;
    SimulatedSerialPort simPort;

    @BeforeEach
    void setup(){
        simPort = new SimulatedSerialPort();
        sensor = new MotionSensor(simPort);
    }

    @Test
    void attach_MotionSensorObserver_to_SimulatedSerialPortObservable(){
        simPort.attach((PortObserver) sensor);
        Assertions.assertThat(simPort.getObserver()).contains((PortObserver) sensor);
    }

    @Test
    void detach_MotionSensorObserver_from_SimulatedSerialPortObservable(){
        simPort.attach((PortObserver) sensor);
        simPort.detach((PortObserver) sensor);
        Assertions.assertThat(simPort.getObserver()).doesNotContain((PortObserver) sensor);
    }
}
