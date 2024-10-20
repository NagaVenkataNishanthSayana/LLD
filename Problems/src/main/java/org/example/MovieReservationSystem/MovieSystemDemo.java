package org.example.MovieReservationSystem;

import java.util.ArrayList;
import java.util.List;

public class MovieSystemDemo {

    public static void demo(){
        MovieBookingSystem movieBookingSystem=MovieBookingSystem.getMovieInstance();

        Movie movie=new Movie("Ride",2.5);
        movieBookingSystem.addMovies(movie);
        List<Movie>movieList=new ArrayList<>();
        movieList.add(movie);

    }
}
