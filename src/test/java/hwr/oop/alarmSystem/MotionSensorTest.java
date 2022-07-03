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
    void motionSensor_constructor_portIsOpen(){
        Assertions.assertThat(simPort.isPortOpen()).isEqualTo(true);
    }

    @Test
    void startListening_isListening(){
        sensor.startListening();
        Assertions.assertThat(simPort.isListening()).isEqualTo(true);
    }

    @Test
    void sendString_activate_isActivated(){
        sensor.activateSensor();
        Assertions.assertThat(simPort.isActivated()).isEqualTo(true);
    }

    @Test
    void writeBytes_deactivate_isDeactivated(){
        sensor.activateSensor();
        sensor.deactivateSensor();
        Assertions.assertThat(simPort.isActivated()).isEqualTo(false);
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
