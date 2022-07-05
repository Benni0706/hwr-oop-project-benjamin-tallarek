package hwr.oop.alarmSystem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonitoringTest {

    SimulatedSerialPort simPort;
    Sensor sensor;
    Monitoring monitoring;

    @BeforeEach
    void setup(){
        simPort = new SimulatedSerialPort();
        sensor = new MotionSensor(simPort);
        monitoring = new Monitoring(sensor);
    }

    @Test
    void attach_MonitoringObserver_to_MotionSensorObservable(){
        sensor.attach(monitoring);
        Assertions.assertThat(sensor.getObserver()).contains(monitoring);
    }

    @Test
    void detach_MonitoringObserver_from_MotionSensorObservable(){
        sensor.attach(monitoring);
        sensor.detach(monitoring);
        Assertions.assertThat(sensor.getObserver()).doesNotContain(monitoring);
    }

    @Test
    void monitoringGetsMessage_fromSimulatedSerialPort_overMotionSensor(){
        simPort.attach((PortObserver) sensor);
        sensor.attach(monitoring);
        simPort.setMessage("message");
        Assertions.assertThat(monitoring.getMessage()).isEqualTo("message");
    }

    @Test
    void motionDetected_fromSimulatedSerialPort_overMotionSensor(){
        simPort.attach((PortObserver) sensor);
        sensor.attach(monitoring);
        simPort.setMessage("motion detected");
        Assertions.assertThat(monitoring.getMessage()).isEqualTo("motion detected");
    }

}
