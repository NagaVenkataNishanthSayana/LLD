package org.example.Elevator;

import java.util.List;

public class ExternalDispatcher {
    List<ElevatorController> elevatorControllerList=ElevatorCreator.elevatorControllerList;

    public void submitExternalRequest(int floor,ElevatorDirection elevatorDirection){

        for(ElevatorController elevatorController:elevatorControllerList){
            int elevatorId=elevatorController.getElevatorCar().getId();
            if(elevatorId%2==1 && floor%2==1){
                elevatorController.submitExternalRequest(floor,elevatorDirection);
            } if(elevatorId%2==0 && floor%2==0){
                elevatorController.submitExternalRequest(floor,elevatorDirection);
            }
        }
    }
}
