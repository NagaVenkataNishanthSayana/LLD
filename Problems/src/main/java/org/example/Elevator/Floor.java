package org.example.Elevator;

public class Floor {

    private int floorNumber;
    private ExternalDispatcher externalDispatcher;

    public Floor(int floorNumber) {
        this.floorNumber=floorNumber;
        this.externalDispatcher=new ExternalDispatcher();
    }

    public void pressButton(ElevatorDirection elevatorDirection){
        externalDispatcher.submitExternalRequest(floorNumber, elevatorDirection);
    }
}
