package com.dsliusar.entity;

public class GenreMovie {

    private int genreMovieId;
    private int movieId;
    private int genreId;

    @Override
    public String toString() {
        return "GenreMovieDao{" +
                "genreMovieId=" + genreMovieId +
                ", movieId=" + movieId +
                ", genreId=" + genreId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreMovie that = (GenreMovie) o;

        if (genreMovieId != that.genreMovieId) return false;
        if (movieId != that.movieId) return false;
        return genreId == that.genreId;

    }

    @Override
    public int hashCode() {
        int result = genreMovieId;
        result = 31 * result + movieId;
        result = 31 * result + genreId;
        return result;
    }

    public int getGenreMovieId() {
        return genreMovieId;
    }

    public void setGenreMovieId(int genreMovieId) {
        this.genreMovieId = genreMovieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
