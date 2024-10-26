package org.example.Practice.Elevator;

public class ElevatorPracticeDemo {

    public static void demo() {

        Elevator elevator=new Elevator();
        ElevatorControl elevatorControl=new ElevatorControl(elevator);

        elevatorControl.submitExternalRequest(4,Direction.UP);
        elevatorControl.submitInternalRequest(5);

        elevatorControl.submitExternalRequest(4,Direction.DOWN);
        elevatorControl.processRequests();
    }

}
