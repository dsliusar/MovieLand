package com.dsliusar.http.entities;

/**
 * Created by DSliusar on 21.06.2016.
 */
public class ReviewAddRequestEntity {
    private int userId;
    private int movieId;
    private String review;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ReviewAddRequest{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", review='" + review + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewAddRequestEntity that = (ReviewAddRequestEntity) o;

        if (userId != that.userId) return false;
        if (movieId != that.movieId) return false;
        return !(review != null ? !review.equals(that.review) : that.review != null);

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + movieId;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        return result;
    }
}
