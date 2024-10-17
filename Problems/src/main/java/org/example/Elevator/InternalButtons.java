package org.example.Elevator;

public class InternalButtons {

    static InternalDispatcher internalDispatcher=new InternalDispatcher();

    public static void pressButton(int floor,ElevatorCar elevatorCar) {
        internalDispatcher.submitInternalRequest(floor,elevatorCar);
    }
}
