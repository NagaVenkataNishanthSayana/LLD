package org.example.Practice.Elevator;

import org.example.Elevator.ElevatorController;

import java.util.*;

public class ElevatorControl {

    private Elevator elevator;
    private final int floors = 10;
    private PriorityQueue<Integer> upMin; // Min-heap for upward requests
    private PriorityQueue<Integer> downMax; // Max-heap for downward requests

    public ElevatorControl(Elevator elevator) {
        this.elevator = elevator;
        upMin = new PriorityQueue<>();
        downMax = new PriorityQueue<>((a, b) -> b - a);
    }

    public void submitInternalRequest(int floor) {
        if (floor > floors) {
            System.out.println("Floor outside reach");
            return;
        }
        // If elevator is IDLE, determine the direction based on the floor requested
        if (elevator.getDirection() == Direction.IDLE) {
            if (elevator.getCurrentFloor() < floor) {
                elevator.setDirection(Direction.UP);
                upMin.add(floor);
            } else if (elevator.getCurrentFloor() > floor) {
                elevator.setDirection(Direction.DOWN);
                downMax.add(floor);
            }
            // If the elevator is already on the requested floor, it can be considered complete
        } else if (elevator.getDirection() == Direction.UP) {
                upMin.add(floor);
        } else { // Direction is DOWN
                downMax.add(floor);
        }
    }

    public void submitExternalRequest(int floor, Direction direction) {
        if (floor > floors) {
            System.out.println("Floor outside reach");
            return;
        }
        // If elevator is IDLE, determine the direction based on the floor requested
        if (elevator.getDirection() == Direction.IDLE) {
            if (direction == Direction.UP) {
                elevator.setDirection(Direction.UP);
                upMin.add(floor);
            } else { // Direction is DOWN
                downMax.add(floor);
                elevator.setDirection(Direction.DOWN);
            }
        } else if (direction == Direction.UP) {
                upMin.add(floor);
        } else { // Direction is DOWN
            downMax.add(floor);
        }
    }

    public void processRequests() {
        while (!upMin.isEmpty() || !downMax.isEmpty() ) {
            if (elevator.getDirection() == Direction.UP) {
                // Process UP requests
                while (!upMin.isEmpty()) {
                    elevator.move(Direction.UP, upMin.poll());
                }
                if(downMax.isEmpty()) {
                    elevator.setDirection(Direction.IDLE); // No requests to proces
                } else {
                    elevator.setDirection(Direction.DOWN);
                }

            } else { // elevator.getDirection() == Direction.DOWN
                // Process DOWN requests
                while (!downMax.isEmpty()) {
                    elevator.move(Direction.DOWN, downMax.poll());
                }

                if(upMin.isEmpty()) {
                    elevator.setDirection(Direction.IDLE); // No requests to proces
                } else {
                    elevator.setDirection(Direction.UP);
                }

            }
        }
    }
}
