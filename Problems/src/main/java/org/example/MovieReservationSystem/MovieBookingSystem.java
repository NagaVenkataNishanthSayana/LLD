package org.example.MovieReservationSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MovieBookingSystem {
    List<Movie> movieList;
    List<Theatre> theatres;
    Map<String,Booking> bookings;
    Map<String,Show> shows;

    private static MovieBookingSystem movieBookingSystemInstance;

    private MovieBookingSystem(){
        movieList=new ArrayList<>();
        this.theatres=new ArrayList<>();
        bookings=new ConcurrentHashMap<>();
        shows= new ConcurrentHashMap<>();
    }

    public static synchronized MovieBookingSystem getMovieInstance(){
        if(movieBookingSystemInstance==null){
            movieBookingSystemInstance=new MovieBookingSystem();
        }

        return movieBookingSystemInstance;
    }

    public void addMovies(Movie movie){
        this.movieList.add(movie);
    }

    public void addTheatre(Theatre theatre){
        this.theatres.add(theatre);
    }

    public void addShow(Show show) {
        shows.put(show.getId(), show);
    }

    public synchronized Booking makeBooking(User user,Show show,List<Seat> seats){
        if(seatsAreAvailable(show,seats)){
            markAsBooked(show,seats);
            double totalPrice=calculatePrice(show,seats);
            Booking booking=new Booking(user,show,seats,totalPrice,BookingStatus.PENDING);
            bookings.put(booking.getId(),booking);
            return booking;
        }
        return null;
    }

    public synchronized void confirmBooking(Booking booking){
        Booking newBooking=bookings.get(booking.getId());

        if(newBooking!=null && newBooking.getStatus()==BookingStatus.PENDING) newBooking.setBookingStatus(BookingStatus.CONFIRMED);
    }

    public synchronized void cancelBooking(Booking booking){
        Booking newBooking=bookings.get(booking.getId());

        if(newBooking!=null && newBooking.getStatus()==BookingStatus.CONFIRMED) newBooking.setBookingStatus(BookingStatus.CANCELLED);
    }

    private double calculatePrice(Show show, List<Seat> seats) {
        double result=0;
        Map<String,Seat>showSeats=show.getSeats();
        for(Seat seat:seats){
            Seat showSeat=showSeats.get(seat.getId());
            result+=showSeat.getPrice();
        }
        return result;
    }

    private void markAsBooked(Show show, List<Seat> seats) {
        Map<String,Seat>showSeats=show.getSeats();
        for(Seat seat:seats){
            Seat showSeat=showSeats.get(seat.getId());
            showSeat.setStaus(SeatStatus.UNAVAILABLE);
        }
    }

    private boolean seatsAreAvailable(Show show, List<Seat> seats) {
        Map<String,Seat>showSeats=show.getSeats();
        for(Seat seat:seats){
            Seat showSeat=showSeats.get(seat.getId());
            if(showSeat==null || showSeat.getStatus()== SeatStatus.UNAVAILABLE) return false;
        }
        return true;
    }
}
