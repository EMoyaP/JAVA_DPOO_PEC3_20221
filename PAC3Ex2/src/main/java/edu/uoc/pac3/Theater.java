package edu.uoc.pac3;

public class Theater {


    private Movie[] movies;

    private String name;
    private int capacity;

    public static final String ERR_WRONG_INDEX = "[ERROR] Wrong index";
    public static final String ERR_NULL_MOVIE = "[ERROR] Movie cannot be null";
    public static final String ERR_NO_MORE_MOVIES = "[ERROR] This theater cannot screen more movies";
    public static final String ERR_MOVIE_EXISTS = "[ERROR] This movies is already screened in this theater";
    public static final String ERR_MOVIE_DOESNT_EXIST = "[ERROR] This movies does not exist in this theater";

    public Theater(String name, int capacity, int MAX_MOVIES) {
        setName(name);
        setCapacity(capacity);
        this.movies = new Movie[MAX_MOVIES];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public Movie getMovie(int index) throws Exception {
        if (index < 0 || index >= movies.length) {
            throw new Exception(ERR_WRONG_INDEX);
        }
        return movies[index];
    }

    private int findMovieIndex(Movie movie) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null && movies[i].equals(movie)) {
                return i;
            }
        }
        return -1;
    }

    public boolean movieExists(Movie movie) {

        if (findMovieIndex(movie) == -1 || movie == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean canScreenMoreMovies() {
        for (int i = 0; i < this.movies.length; i++) {
            if (this.movies[i] == null) {
                return true;
            }
        }
        return false;
    }

    private void setMovie(int index, Movie movie) throws Exception {
        if (index < 0 || index >= movies.length) {
            throw new Exception(ERR_WRONG_INDEX);
        }
        this.movies[index] = movie;
    }

    public void addMovie(Movie movie) throws Exception {
        if (movie == null) {
            throw new Exception(ERR_NULL_MOVIE);
        }
        if (!canScreenMoreMovies()) {
            throw new Exception(ERR_NO_MORE_MOVIES);
        }
        if (movieExists(movie)) {
            throw new Exception(ERR_MOVIE_EXISTS);
        }
        for (int i = 0; i < this.movies.length; i++) {
            if (this.getMovie(i) == null && !movieExists(movie)) {
                setMovie(i, movie);
            }
            if (!movie.isInTheTheater(this)) {
                movie.addTheater(this);
            }
        }
    }

    public void removeMovie(Movie movie) throws Exception {
        if (!movieExists(movie)) {
            throw new Exception(ERR_MOVIE_DOESNT_EXIST);
        }
        int index = findMovieIndex(movie);
        setMovie(index, null);
        movie.removeTheater(this);
    }

}
