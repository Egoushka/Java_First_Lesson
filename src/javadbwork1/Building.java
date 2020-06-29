package javadbwork1;

import java.util.ArrayList;

public class Building {
    public ArrayList<Floor> floors;
    private Elevator elevator;
    private String adress;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    Building(){
        elevator = new Elevator();        
        floors = new ArrayList<Floor>();
    }

}
