package org.example.ParingLot;

import org.example.ParingLot.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<ParkingLevel> parkingLevels;
    static ParkingLot instance;

    private ParkingLot(){
        parkingLevels=new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance(){
        if(instance==null){
            instance=new ParkingLot();
        }
        return instance;
    }

    public void addLevels(ParkingLevel level){
        parkingLevels.add(level);
    }

    public synchronized boolean parkVehicle(Vehicle vehicle){
        for(ParkingLevel level:parkingLevels){
            if(level.parkVehicle(vehicle)) return true;
        }
        return false;
    }

    public synchronized boolean unParkVehicle(Vehicle vehicle){
        for(ParkingLevel level:parkingLevels){
            if(level.unParkVehicle(vehicle)) return true;
        }
        return false;
    }

    public synchronized void displayParkedSpots(){
        for (ParkingLevel level:parkingLevels){
            level.displayFilledSpots();
        }
    }
}
