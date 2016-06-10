package com.dsliusar.entity;

public class Genre {
    private int genreId;
    private String descprtion;

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", descprtion='" + descprtion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (genreId != genre.genreId) return false;
        if (descprtion != null ? !descprtion.equals(genre.descprtion) : genre.descprtion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genreId;
        result = 31 * result + (descprtion != null ? descprtion.hashCode() : 0);
        return result;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getDescprtion() {
        return descprtion;
    }

    public void setDescprtion(String descprtion) {
        this.descprtion = descprtion;
    }
}
