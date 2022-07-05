package hwr.oop.alarmSystem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ConsoleTest {

    SimulatedSerialPort simPort;
    Sensor sensor;
    Monitoring monitoring;
    InputStream in;
    OutputStream out;

    private void setup(String input){
        simPort = new SimulatedSerialPort();
        sensor = new MotionSensor(simPort);
        in = createInputStreamForInput(input);
        out = new ByteArrayOutputStream();
        monitoring = new Monitoring(sensor, in, out);
    }

    @Test
    void console_inputActivate_testOutput(){
        setup("activate");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("activated the sensor\n");
    }

    @Test
    void console_inputDeactivate_testOutput(){
        setup("deactivate");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("deactivated the sensor\n");
    }

    @Test
    void console_inputUnknownCommand_testOutput(){
        setup("test123");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("unknown command\navailable commands: 'activate', 'deactivate', 'exit'\n");
    }

    @Test
    void console_InputExit_testOutput(){
        setup("exit");
        monitoring.checkInput();
        Assertions.assertThat(out.toString()).isEqualTo("exiting");
    }

    @Test
    void console_NoInput_test(){
        setup("");
        Assertions.assertThat(monitoring.checkInput() == true);
    }

    @Test
    void update_motionDetected_testOutput(){
        setup("");
        monitoring.update("motion detected");
        Assertions.assertThat(out.toString()).isEqualTo("The Sensor detected motion at\n");
    }

    @Test
    void update_message_testOutput(){
        setup("");
        monitoring.update("test123");
        Assertions.assertThat(out.toString()).isEqualTo("Message from sensor: test123");
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
}
