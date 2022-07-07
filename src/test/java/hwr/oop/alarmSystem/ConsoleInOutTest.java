package hwr.oop.alarmSystem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ConsoleInOutTest {

    SimulatedSerialPort simPort;
    Sensor sensor;
    Monitoring monitoring;
    InputStream in;
    OutputStream out;

    private void setupWithInput(String input){
        simPort = new SimulatedSerialPort();
        sensor = new MotionSensor(simPort);
        in = createInputStreamForInput(input);
        out = new ByteArrayOutputStream();
        monitoring = new Monitoring(sensor, in, out);
        sensor.attach(monitoring);
        simPort.attach((PortObserver) sensor);
    }

    private void setupWithoutInput(){
        simPort = new SimulatedSerialPort();
        sensor = new MotionSensor(simPort);
        out = new ByteArrayOutputStream();
        monitoring = new Monitoring(sensor, System.in, out);
        sensor.attach(monitoring);
        simPort.attach((PortObserver) sensor);
    }

    @Test
    void console_startListening_testOutput(){
        setupWithoutInput();
        sensor.startListening();
        Assertions.assertThat(out.toString()).isEqualTo("startedListening\n");
    }

    @Test
    void console_inputActivate_testOutput(){
        setupWithInput("activateSensor");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("sensorActivated\n");
    }

    @Test
    void console_inputDeactivate_testOutput(){
        setupWithInput("deactivateSensor");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("sensorDeactivated\n");
    }

    @Test
    void console_inputUnknownCommand_testOutput(){
        setupWithInput("test123");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("unknown command\navailable commands: 'activate', 'deactivate', 'exit'\n");
    }

    @Test
    void console_InputExit_testOutput(){
        setupWithInput("exit");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("exiting");
    }

    @Test
    void update_motionDetected_testOutput(){
        setupWithoutInput();
        monitoring.update("motionDetected");
        Assertions.assertThat(out.toString()).isEqualTo("motionDetected\n");
    }

    @Test
    void update_message_testOutput(){
        setupWithInput("");
        monitoring.update("test123");
        Assertions.assertThat(out.toString()).isEqualTo("test123\n");
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
}
