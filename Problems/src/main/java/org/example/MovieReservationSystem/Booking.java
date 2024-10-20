package org.example.MovieReservationSystem;

import java.util.List;

public class Booking {

    private String id;
    private double totalPrice;
    User user;
    private List<Seat> seats;
    private Show show;
    private BookingStatus bookingStatus;

    public Booking(User user, Show show, List<Seat> seats, double totalPrice, BookingStatus status) {
        this.user=user;
        this.show=show;
        this.totalPrice=totalPrice;
        this.bookingStatus=status;
        this.seats=seats;
    }

    public String getId() {
        return id;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingStatus getStatus() {
        return this.bookingStatus;
    }
}
