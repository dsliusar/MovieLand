package com.dsliusar.tools.entities.http;

public class MovieRatingChangeRequest {
    private int movieId;
    private double rating;
    private int userId;

    public int getMovieId() { return movieId; }

    public void setMovieId(int movieId) {this.movieId = movieId;}

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    @Override
    public String toString() {
        return "MovieRatingChangeRequest{" +
                "movieId=" + movieId +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieRatingChangeRequest that = (MovieRatingChangeRequest) o;

        if (movieId != that.movieId) return false;
        return Double.compare(that.rating, rating) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = movieId;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
