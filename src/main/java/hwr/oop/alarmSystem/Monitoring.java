package hwr.oop.alarmSystem;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Monitoring implements SensorObserver{

    private Sensor sensor;
    private String message;
    private Scanner in;
    private PrintStream out;

    Monitoring(Sensor sensor, InputStream in, OutputStream out){
        this.sensor = sensor;
        this.in = new Scanner(in);
        this.out = new PrintStream(out);
    }

    Monitoring(Sensor sensor){
        this(sensor, System.in, System.out);
    }

    String getMessage() {
        return message;
    }

    @Override
    public void update(String message) {
        this.message = message;
        if(message == "motion detected"){
            out.print("The Sensor detected motion at\n");
        }else{
            out.print("Message from sensor: " + message + "\n");
        }
    }

    boolean checkInput(){
        String input;
        if(in.hasNextLine()){
            input = in.next();
            if(input.equals("activate")){
                sensor.activateSensor();
                out.print("activated the sensor\n");
            }else if(input.equals("deactivate")){
                sensor.deactivateSensor();
                out.print("deactivated the sensor\n");
            }else if(input.equals("exit")){
                out.print("exiting");
                return false;
            }else{
                out.print("unknown command\navailable commands: 'activate', 'deactivate', 'exit'\n");
            }
        }return true;
    }
}