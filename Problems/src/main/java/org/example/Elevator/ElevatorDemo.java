package org.example.Elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorDemo {

    public static void demo(){
        Building building=new Building(new ArrayList<Floor>());

        for(int i=0;i<=10;i++){
            building.addFloors(new Floor(i));
        }


//        for(int i=6;i<=10;i++){
//            building.getAllFloorList().get(i).pressButton(ElevatorDirection.DOWN);
//        }



        List<ElevatorController> elevatorControllerList=ElevatorCreator.elevatorControllerList;

        for (int i=0;i<=10;i=i+3){
             InternalButtons.pressButton(i,elevatorControllerList.get(1).getElevatorCar());
        }



        for(ElevatorController elevatorController:elevatorControllerList){
            System.out.println("Elevator ID: "+elevatorController.getElevatorCar().getId());
            elevatorController.processRequests();
        }

        for(int i=1;i<=5;i=i+1){
            building.getAllFloorList().get(i).pressButton(ElevatorDirection.UP);
        }

        for(ElevatorController elevatorController:elevatorControllerList){
            System.out.println("Elevator ID: "+elevatorController.getElevatorCar().getId());
            elevatorController.processRequests();
        }

    }
}
