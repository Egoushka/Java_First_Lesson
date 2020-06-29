package javadbwork1;
import java.util.ArrayList;


public class Elevator {
    public ArrayList<Passanger> passanger;
    private int currentFloor;
    private boolean direction;
    
    public Elevator() {
        passanger = new ArrayList<>();     
    }
    
    public boolean isDirection() {
        return direction;
    }
    public void setDirection(boolean direction) {
        this.direction = direction;
    }
    
    public int getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    
   
    
}
