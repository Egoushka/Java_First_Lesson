package javadbwork1;

import java.util.ArrayList;


public class Floor {
    public ArrayList<Passanger> passangers;
    String name;

    public Floor() {
        passangers = new ArrayList<Passanger>();
    }
     public Floor(String name) {
         this.name = name;
         passangers = new ArrayList<Passanger>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
