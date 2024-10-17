package org.example.Elevator;

public class ElevatorDisplay {
    private int floor;
    private ElevatorDirection direction;

    public void setDisaplay(int floor,ElevatorDirection elevatorDirection){
        this.floor=floor;
        this.direction=elevatorDirection;
    }

    public void showDisplay(){
        System.out.println("Floor: "+this.floor+" | Direction: "+this.direction);
    }
}
