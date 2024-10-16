package org.example.ParingLot;

import org.example.ParingLot.Vehicles.Vehicle;
import org.example.ParingLot.Vehicles.VehicleType;

public class ParkingSpot {
    private Vehicle parkedVehicle;
    private VehicleType vehicleType;

    private int spotNumber;

    public ParkingSpot(int spotNumber,VehicleType vehicleType){
        this.vehicleType=vehicleType;
        this.spotNumber=spotNumber;
    }
    public synchronized boolean isSpotAvailable(){
        return parkedVehicle==null;
    }

    public synchronized boolean parkVehicle(Vehicle vehicle){
        parkedVehicle=vehicle;
        return true;
    }

    public synchronized boolean unParkVehicle(Vehicle vehicle){
        parkedVehicle=null;
        return true;
    }

    public synchronized int getSpotNumber() {
        return this.spotNumber;
    }

    public synchronized VehicleType getVehicleType(){
         return vehicleType;
    }

    public synchronized Vehicle getParkedVehicle(){
        return parkedVehicle;
    }
}
