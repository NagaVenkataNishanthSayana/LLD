package org.example.Elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorCreator {

    static List<ElevatorController> elevatorControllerList=new ArrayList<>();

    static {
        ElevatorCar elevatorCar1=new ElevatorCar(1);
        elevatorCar1.setElevatorDirection(ElevatorDirection.UP);

        ElevatorController elevatorController1=new ElevatorController(elevatorCar1);

        ElevatorCar elevatorCar2=new ElevatorCar(2);
        elevatorCar2.setElevatorDirection(ElevatorDirection.UP);

        ElevatorController elevatorController2=new ElevatorController(elevatorCar2);

        elevatorControllerList.add(elevatorController1);
        elevatorControllerList.add(elevatorController2);
    }
}
