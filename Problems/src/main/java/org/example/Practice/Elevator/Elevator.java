package org.example.Practice.Elevator;

import org.example.Elevator.ElevatorCar;

public class Elevator {

    private int currentFloor;
    private Direction direction;

    public Elevator(){
        this.direction=Direction.UP;
        this.currentFloor=0;
    }

    public Direction getDirection(){
        return this.direction;

    }

    public void setDirection(Direction direction){
        this.direction=direction;
    }

    public int getCurrentFloor(){
        return this.currentFloor;
    }

    public void setCurrentFloor(int currentFloor){
        this.currentFloor=currentFloor;
    }

    public void move(Direction direction,int destinationFloor){
        if(direction==Direction.UP){
            for(int i=currentFloor;i<destinationFloor;i++){
                System.out.println("Moving UP | floor: "+i);
            }
            System.out.println("Reached Destination: "+ destinationFloor);
            currentFloor=destinationFloor;
        }

        if(direction==Direction.DOWN){
            for(int i=currentFloor;i>=destinationFloor;i--){
                System.out.println("Moving Down | floor: "+i);
            }
            System.out.println("Reached Destination: "+ destinationFloor);
            currentFloor=destinationFloor;
        }


    }
}
