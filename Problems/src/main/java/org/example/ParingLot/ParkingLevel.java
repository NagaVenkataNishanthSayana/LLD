package org.example.ParingLot;

import org.example.ParingLot.Vehicles.Vehicle;
import org.example.ParingLot.Vehicles.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {

    private List<ParkingSpot> parkingSpots;
    private int floor;
    private int levelCapacity=100;

    private final double carRatio=0.6;
    private final double truckRatio=0.3;
    private final double motorCycleRatio=0.1;

    public ParkingLevel(int floor,int levelCapacity){
        this.floor =floor;
        this.levelCapacity=levelCapacity;
        parkingSpots=new ArrayList<>();
        assignSpots();
    }

    //Assign spots accordingly
    public void assignSpots(){
        for (int i=0;i<levelCapacity*carRatio;i++){
            parkingSpots.add(new ParkingSpot(i,VehicleType.CAR));
        }

        for (int i=0;i<levelCapacity*truckRatio;i++){
            parkingSpots.add(new ParkingSpot(i,VehicleType.TRUCK));
        }

        for (int i=0;i<levelCapacity*motorCycleRatio;i++){
            parkingSpots.add(new ParkingSpot(i,VehicleType.MOTORCYCLE));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle){
        for(ParkingSpot spot:parkingSpots){
            if(spot.isSpotAvailable() && spot.getVehicleType()==vehicle.getVehicleType()){
                if(spot.parkVehicle(vehicle)){
                    System.out.println("Vehicle Parked"+" "+vehicle.getVehicleType());
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized boolean unParkVehicle(Vehicle vehicle){
        for(ParkingSpot spot:parkingSpots){
            if(!spot.isSpotAvailable() && vehicle==spot.getParkedVehicle()){
                if(spot.unParkVehicle(vehicle)){
                    System.out.println("Vehicle unParked"+" "+vehicle.getVehicleType());
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized int carSpotsAvailability(){
        int count=0;
        for(ParkingSpot spot:parkingSpots){
            if(spot.isSpotAvailable() && spot.getVehicleType()==VehicleType.CAR){
                count++;
            }
        }
        return count;
    }

    public synchronized int TruckSpotsAvailability(){
        int count=0;
        for(ParkingSpot spot:parkingSpots){
            if(spot.isSpotAvailable() && spot.getVehicleType()==VehicleType.TRUCK){
                count++;
            }
        }
        return count;
    }

    public synchronized int motorcycleSpotsAvailability(){
        int count=0;
        for(ParkingSpot spot:parkingSpots){
            if(spot.isSpotAvailable() && spot.getVehicleType()==VehicleType.MOTORCYCLE){
                count++;
            }
        }
        return count;
    }

    public synchronized void displayFilledSpots(){
        System.out.println("Level: "+this.floor);
        for(ParkingSpot spot:parkingSpots){
            if(!spot.isSpotAvailable()){
                System.out.println(spot.getParkedVehicle());
            }
        }
    }

}
