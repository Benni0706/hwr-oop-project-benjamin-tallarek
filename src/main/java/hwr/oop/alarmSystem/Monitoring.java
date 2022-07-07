package hwr.oop.alarmSystem;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Monitoring implements SensorObserver{

    private Sensor sensor;
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

    @Override
    public void update(String message) {
        out.print(message + "\n");
    }

    boolean checkInput(){
        String input;
        if(in.hasNextLine()){
            input = in.next();
            if(input.equals("activateSensor")){
                sensor.activateSensor();
            }else if(input.equals("deactivateSensor")){
                sensor.deactivateSensor();
            }else if(input.equals("exit")){
                out.print("exiting");
                return false;
            }else{
                out.print("unknown command\navailable commands: 'activate', 'deactivate', 'exit'\n");
            }
        }return true;
    }
}