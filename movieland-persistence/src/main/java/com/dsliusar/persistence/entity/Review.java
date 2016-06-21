package com.dsliusar.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = { "reviewId","movieId","userId"})
public class Review implements Serializable{

    @JsonIgnore
    private int reviewId;
    @JsonIgnore
    private int movieId;
    @JsonIgnore
    private int userId;
    private String reviewText;

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", movieId=" + movieId +
                ", userId=" + userId +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (movieId != review.movieId) return false;
        if (reviewId != review.reviewId) return false;
        if (userId != review.userId) return false;
        if (reviewText != null ? !reviewText.equals(review.reviewText) : review.reviewText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + movieId;
        result = 31 * result + userId;
        result = 31 * result + (reviewText != null ? reviewText.hashCode() : 0);
        return result;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
