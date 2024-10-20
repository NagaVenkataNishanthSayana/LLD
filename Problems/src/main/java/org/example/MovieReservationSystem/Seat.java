package org.example.MovieReservationSystem;

public class Seat {
    private String id;
    private SeatStatus seatStatus;
    private double price;

    public String getId() {
        return this.id;
    }

    public SeatStatus getStatus() {
        return this.seatStatus;
    }

    public void setStaus(SeatStatus staus) {
        this.seatStatus=SeatStatus.UNAVAILABLE;
    }

    public double getPrice() {
        return this.price;
    }
}
