package hwr.oop.alarmSystem;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SimulatedSerialPort implements SerialPort {
    private List<PortObserver> observer = new ArrayList<>();
    private boolean portOpen;
    private boolean activated;
    private boolean listening;

    SimulatedSerialPort(){
        portOpen = false;
        activated = false;
        listening = false;
    }

    @Override
    public List<PortObserver> getObserver() {
        return observer;
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public boolean isPortOpen() {
        return portOpen;
    }

    @Override
    public boolean isListening() {
        return listening;
    }

    @Override
    public void openPort() {
        portOpen = true;
    }

    @Override
    public void writeBytes(byte[] buffer, int length) {
        String input = new String(buffer, StandardCharsets.UTF_8);
        if(input.equals("activate")){
            activated = true;
        }else if(input.equals("deactivate")){
            activated = false;
        }
    }

    @Override
    public void startListening() {
        listening = true;
    }

    @Override
    public void attach(PortObserver observer) {
        this.observer.add(observer);
    }

    @Override
    public void detach(PortObserver observer) {
        this.observer.remove(observer);
    }

    @Override
    public void setMessage(String message) {
        for( PortObserver observer : this.observer){
            observer.update(message);
        }
    }
}
