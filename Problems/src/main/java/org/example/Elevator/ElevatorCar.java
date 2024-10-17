package org.example.Elevator;

public class ElevatorCar {

    private int id;
    private ElevatorDirection elevatorDirection;
    private ElevatorState elevatorState;
    private ElevatorDisplay elevatorDisplay;
    private int currentFloor;
    private InternalButtons internalButtons;


    public ElevatorCar(int id) {
        this.id=id;
        elevatorDisplay=new ElevatorDisplay();
        internalButtons=new InternalButtons();
        currentFloor=0;
        elevatorState=ElevatorState.IDLE;
        elevatorDirection=ElevatorDirection.UP;
    }

    public int getId() {
        return id;
    }

    public ElevatorDirection getElevatorDirection() {
        return elevatorDirection;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

//    public void pressButton(int destination){
//        internalButtons.pressButton(destination,this);
//    }

    public void setElevatorDisplay(){
        elevatorDisplay.setDisaplay(this.currentFloor,this.elevatorDirection);
    }

    public void setElevatorDirection(ElevatorDirection elevatorDirection) {
        this.elevatorDirection = elevatorDirection;
    }

    public void showDisplay(){
        elevatorDisplay.showDisplay();
    }

    public boolean moveElevator(ElevatorDirection direction,int destinationFloor){
        int startFloor=currentFloor;
        if(direction==ElevatorDirection.UP){
            for(int i=startFloor+1;i<=destinationFloor;i++){
                this.currentFloor=i;
                setElevatorDisplay();
                showDisplay();
                if(i==destinationFloor-1) return true;
            }
        }

        if(direction==ElevatorDirection.DOWN){
            for(int i=startFloor;i>=destinationFloor;i--){
                this.currentFloor=i;
                setElevatorDisplay();
                showDisplay();
                if(i==destinationFloor) return true;
            }
        }
        return false;
    }
}
