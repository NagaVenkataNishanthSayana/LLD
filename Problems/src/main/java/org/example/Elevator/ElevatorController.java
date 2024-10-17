package org.example.Elevator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ElevatorController {

    private PriorityQueue<Integer> upMinPQ;
    private PriorityQueue<Integer> downMaxPq;
    private ElevatorCar elevatorCar;

    // Pending requests that don't match the current direction
    private Queue<Integer> pendingUpRequests = new LinkedList<>();
    private Queue<Integer> pendingDownRequests = new LinkedList<>();

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        upMinPQ = new PriorityQueue<>();  // Min-heap for upward requests
        downMaxPq = new PriorityQueue<>((a, b) -> (b - a));  // Max-heap for downward requests
    }

    public ElevatorCar getElevatorCar() {
        return elevatorCar;
    }

    /**
     * Handles internal requests based on the current direction of the elevator.
     * If the request doesn't align with the current direction, it's stored in a pending queue.
     */
    public void submitInternalRequest(int floor) {
        if (elevatorCar.getElevatorDirection() == ElevatorDirection.UP) {
            if (floor < elevatorCar.getCurrentFloor()) {
                pendingUpRequests.add(floor);  // Can't go down while going up
            } else {
                upMinPQ.add(floor);
            }
        } else { // Elevator is moving down
            if (floor > elevatorCar.getCurrentFloor()) {
                pendingDownRequests.add(floor);  // Can't go up while going down
            } else {
                downMaxPq.add(floor);
            }
        }
    }

    /**
     * Handles external requests. Adds them to the appropriate queue based on the requested direction.
     */
    public void submitExternalRequest(int floor, ElevatorDirection requestedDirection) {
        if (requestedDirection == ElevatorDirection.UP) {
            if (floor < elevatorCar.getCurrentFloor()) {
                pendingUpRequests.add(floor);  // Floor is below, can't handle now
            } else {
                upMinPQ.add(floor);  // Add to the upward queue
            }
        } else {  // Downward request
            if (floor > elevatorCar.getCurrentFloor()) {
                pendingDownRequests.add(floor);  // Floor is above, add to pending
            } else {
                downMaxPq.add(floor);  // Add to the downward queue
            }
        }
    }

    public void processRequests() {
        while (!upMinPQ.isEmpty() || !downMaxPq.isEmpty() || !pendingUpRequests.isEmpty() || !pendingDownRequests.isEmpty()) {
            // Handle pending down requests when there are no up requests but there are down ones
            if (upMinPQ.isEmpty() && downMaxPq.isEmpty() && !pendingDownRequests.isEmpty()) {
                while (!pendingDownRequests.isEmpty()) {
                    downMaxPq.add(pendingDownRequests.poll());
                }
                if (elevatorCar.getCurrentFloor() < downMaxPq.peek()) {
                    elevatorCar.setElevatorDirection(ElevatorDirection.UP);
                    upMinPQ.add(downMaxPq.peek());
                }
            }

            //Handling pending UP requests when all other requests are empty
            if (upMinPQ.isEmpty() && downMaxPq.isEmpty() && !pendingUpRequests.isEmpty()) {
                int lowestPendingUpRequest = pendingUpRequests.stream().min(Integer::compare).orElse(elevatorCar.getCurrentFloor());

                // Move downward to the lowest pending upward request
                if (elevatorCar.getCurrentFloor() > lowestPendingUpRequest) {
                    elevatorCar.setElevatorDirection(ElevatorDirection.DOWN);
                    downMaxPq.add(lowestPendingUpRequest);
                }
                // Now, add those pending upward requests to the queue to process
                while (!pendingUpRequests.isEmpty()) {
                    upMinPQ.add(pendingUpRequests.poll());
                }
            }

            // If the elevator is moving up, handle upward requests
            if (elevatorCar.getElevatorDirection() == ElevatorDirection.UP) {
                while (!upMinPQ.isEmpty()) {
                    int destinationFloor = upMinPQ.poll();
                    if (elevatorCar.moveElevator(ElevatorDirection.UP, destinationFloor)) {
                        System.out.println("Elevator stopped at destination floor: " + destinationFloor);
                    }
                }
                // Move any pending upward requests to the active queue
                while (!pendingUpRequests.isEmpty()) {
                    upMinPQ.add(pendingUpRequests.poll());
                }



                // Set direction to DOWN if there are no upward requests left
                elevatorCar.setElevatorDirection(ElevatorDirection.DOWN);
            }

            // If the elevator is moving down, handle downward requests
            else {
                while (!downMaxPq.isEmpty()) {
                    int destinationFloor = downMaxPq.poll();
                    if (elevatorCar.moveElevator(ElevatorDirection.DOWN, destinationFloor)) {
                        System.out.println("Elevator stopped at destination floor: " + destinationFloor);
                    }
                }
                // Move any pending downward requests to the active queue
                while (!pendingDownRequests.isEmpty()) {
                    downMaxPq.add(pendingDownRequests.poll());
                }
                // Set direction to UP if there are no downward requests left
                elevatorCar.setElevatorDirection(ElevatorDirection.UP);
            }
        }
    }
}
