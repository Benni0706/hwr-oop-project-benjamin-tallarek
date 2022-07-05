package hwr.oop.alarmSystem;

import java.io.PrintStream;
import java.util.Scanner;

class Monitoring implements SensorObserver{

    private Sensor sensor;
    private String message;
    Scanner in;
    PrintStream out;

    Monitoring(Sensor sensor){
        this.sensor = sensor;
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void update(String message) {
        this.message = message;
        if(message == "motion detected"){
            out.println("The Sensor detected motion at: " + java.time.LocalDateTime.now() );
        }else{
            out.println("Message from sensor: " + message);
        }
    }

    public void startMonitoring() {
        String input;
        out.println("started monitoring");
        out.println("available commands: 'activate', 'deactivate', 'exit'");
        while(true){
            if(in.hasNextLine()){
                input = in.next();
                if(input.equals("activate")){
                    sensor.activateSensor();
                    out.println("activated the sensor");
                }else if(input.equals("deactivate")){
                    sensor.deactivateSensor();
                    out.println("deactivated the sensor");
                }else if(input.equals("exit")){
                    return;
                }else{
                    out.println("unknown command");
                }
            }
        }
    }
}
