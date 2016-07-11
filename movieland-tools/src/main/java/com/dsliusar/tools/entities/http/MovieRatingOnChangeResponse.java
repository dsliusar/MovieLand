package com.dsliusar.tools.entities.http;

public class MovieRatingOnChangeResponse {
    private int movieId;
    private double userRating;
    private double averageRating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "MovieRatingOnChangeResponse{" +
                "movieId=" + movieId +
                ", userRating=" + userRating +
                ", averageRating=" + averageRating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieRatingOnChangeResponse that = (MovieRatingOnChangeResponse) o;

        if (movieId != that.movieId) return false;
        if (Double.compare(that.userRating, userRating) != 0) return false;
        return Double.compare(that.averageRating, averageRating) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = movieId;
        temp = Double.doubleToLongBits(userRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(averageRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
