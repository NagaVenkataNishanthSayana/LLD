package org.example.Elevator;

import java.util.List;

public class InternalDispatcher {

    List<ElevatorController> elevatorControllerList=ElevatorCreator.elevatorControllerList;


    public void submitInternalRequest(int floor, ElevatorCar elevatorCar) {
        // Find the elevator controller responsible for the given elevatorCar
        for (ElevatorController elevatorController : elevatorControllerList) {
            if (elevatorController.getElevatorCar().getId() == elevatorCar.getId()) {

                // Apply odd/even logic for internal requests
                int elevatorId = elevatorCar.getId();

                // Elevator with odd ID should handle odd-numbered floors, even ID should handle even-numbered floors
                if (elevatorId % 2 == 1 && floor % 2 == 1) {
                    // Handle internal request if both the elevator and the floor are odd
                    elevatorController.submitInternalRequest(floor);
                } else if (elevatorId % 2 == 0 && floor % 2 == 0) {
                    // Handle internal request if both the elevator and the floor are even
                    elevatorController.submitInternalRequest(floor);
                } else {
                    // Ignore the request if it doesn't match the odd/even rule for the elevator
                    System.out.println("Floor " + floor + " request ignored by elevator " + elevatorId + " due to odd/even floor rule.");
                }
                break; // Exit the loop after finding the correct elevator
            }
        }
    }
}
