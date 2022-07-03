package hwr.oop.alarmSystem;

import java.io.IOException;

class Monitoring implements SensorObserver{

    private Sensor sensor;
    private String message;

    Monitoring(Sensor sensor){
        this.sensor = sensor;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void update(String message) {
        this.message = message;
        if(message == "motion detected"){
            System.out.println("The Sensor detected motion at" + java.time.LocalDateTime.now() );
        }
    }

    public static void main(String[] args) throws IOException {
        SerialPort serialPort = new SimulatedSerialPort();
        //SerialPort serialPort = new SerialPortAdapter(com.fazecast.jSerialComm.SerialPort.getCommPort("COM5"));

        Sensor sensor = new MotionSensor(serialPort);
        Monitoring monitoring = new Monitoring(sensor);
        sensor.attach(monitoring);
        serialPort.attach((PortObserver) sensor);

    }
}
