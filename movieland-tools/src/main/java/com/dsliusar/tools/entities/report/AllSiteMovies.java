package com.dsliusar.tools.entities.report;

import com.dsliusar.tools.annotations.ReportFieldNames;

import java.sql.Timestamp;

public class AllSiteMovies {

    @ReportFieldNames(fieldName = "Movie id")
    private Integer movieId;

    @ReportFieldNames(fieldName = "Russian name")
    private String movieNameRus;

    @ReportFieldNames(fieldName = "Origin name")
    private String movieNameOrigin;

    @ReportFieldNames(fieldName = "Description")
    private String description;

    @ReportFieldNames(fieldName = "Genres")
    private String genres;

    @ReportFieldNames(fieldName = "Price")
    private Double price;

    @ReportFieldNames(fieldName = "Add date")
    private Timestamp addDate;

    @ReportFieldNames(fieldName = "Modified Date")
    private Timestamp modifiedDate;

    @ReportFieldNames(fieldName = "Rating")
    private Double rating;

    @ReportFieldNames(fieldName = "Reviews Count")
    private Integer reviewCount;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieNameRus() {
        return movieNameRus;
    }

    public void setMovieNameRus(String movieNameRus) {
        this.movieNameRus = movieNameRus;
    }

    public String getMovieNameOrigin() {
        return movieNameOrigin;
    }

    public void setMovieNameOrigin(String movieNameOrigin) {
        this.movieNameOrigin = movieNameOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Override
    public String toString() {
        return "AllSiteMovies{" +
                "movieId=" + movieId +
                ", movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", price=" + price +
                ", modifiedDate=" + modifiedDate +
                ", addDate=" + addDate +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllSiteMovies that = (AllSiteMovies) o;

        if (movieId != null ? !movieId.equals(that.movieId) : that.movieId != null) return false;
        if (movieNameRus != null ? !movieNameRus.equals(that.movieNameRus) : that.movieNameRus != null) return false;
        if (movieNameOrigin != null ? !movieNameOrigin.equals(that.movieNameOrigin) : that.movieNameOrigin != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (genres != null ? !genres.equals(that.genres) : that.genres != null) return false;
          if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (addDate != null ? !addDate.equals(that.addDate) : that.addDate != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        return !(reviewCount != null ? !reviewCount.equals(that.reviewCount) : that.reviewCount != null);

    }

    @Override
    public int hashCode() {
        int result = movieId != null ? movieId.hashCode() : 0;
        result = 31 * result + (movieNameRus != null ? movieNameRus.hashCode() : 0);
        result = 31 * result + (movieNameOrigin != null ? movieNameOrigin.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (addDate != null ? addDate.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (reviewCount != null ? reviewCount.hashCode() : 0);
        return result;
    }
}
