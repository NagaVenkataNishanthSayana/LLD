package org.example.MovieReservationSystem;

import java.time.LocalDateTime;
import java.util.Map;

public class Show {
    private String id;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String,Seat> seats;

    public Show(String id, Movie movie, LocalDateTime startTime, LocalDateTime endTime, Map<String, Seat> seats) {
        this.id = id;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
    }

    public String getId() {
        return this.id;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }
}
