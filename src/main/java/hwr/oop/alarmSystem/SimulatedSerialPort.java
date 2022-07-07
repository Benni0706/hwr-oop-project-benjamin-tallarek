package hwr.oop.alarmSystem;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class SimulatedSerialPort implements SerialPort {
    private List<PortObserver> observer = new ArrayList<>();

    @Override
    public List<PortObserver> getObserver() {
        return observer;
    }

    @Override
    public void openPort() {
        setMessage("portIsOpen");
    }

    @Override
    public void writeBytes(byte[] buffer, int length) {
        String input = new String(buffer, StandardCharsets.UTF_8);
        if(input.equals("activateSensor")){
            setMessage("sensorActivated");
        }else if(input.equals("deactivateSensor")){
            setMessage("sensorDeactivated");
        }
    }

    @Override
    public void startListening() {
        setMessage("startedListening");
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
