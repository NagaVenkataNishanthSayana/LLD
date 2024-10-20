package org.example.MovieReservationSystem;

import java.util.List;

public class Theatre {
    List<Movie> moviesList;
    List<Show>shows;

    public Theatre(List<Movie> moviesList, List<Show> shows) {
        this.moviesList = moviesList;
        this.shows = shows;
    }
}
