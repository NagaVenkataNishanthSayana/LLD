package org.example.ParingLot;

import org.example.ParingLot.Vehicles.Vehicle;
import org.example.ParingLot.Vehicles.VehicleType;

public class ParkingLotDemo {

    public static void demo(){
        ParkingLot parkingLot=ParkingLot.getInstance();

        parkingLot.addLevels(new ParkingLevel(1,100));
        parkingLot.addLevels(new ParkingLevel(2,80));
        parkingLot.addLevels(new ParkingLevel(3,60));

        Vehicle car=new Vehicle("Car1", VehicleType.CAR);

        Vehicle truck=new Vehicle("Truck1",VehicleType.TRUCK);

        Vehicle motorCycle=new Vehicle("Bike1",VehicleType.MOTORCYCLE);
        parkingLot.displayParkedSpots();
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorCycle);
        parkingLot.displayParkedSpots();
        parkingLot.unParkVehicle(truck);
        parkingLot.displayParkedSpots();
    }
}
