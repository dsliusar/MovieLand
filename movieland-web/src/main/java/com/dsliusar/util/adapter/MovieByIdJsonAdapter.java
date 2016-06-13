package com.dsliusar.util.adapter;

import com.dsliusar.entity.Country;
import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Movie;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class MovieByIdJsonAdapter extends TypeAdapter<Movie> {
    @Override
    public void write(JsonWriter out, final Movie movie) throws IOException {
        out.beginObject();
        //out.beginArray();
        out.name("movieNameRus").value(movie.getMovieNameRus());
        out.name("movieNameEng").value(movie.getMovieNameOrigin());
        out.name("year").value(movie.getYear());
        out.name("rating").value(movie.getRating());

        out.name("genres");
        out.beginArray();
        for (Genre genre : movie.getGenreList()) {
            out.value(genre.getName());
        }
        out.endArray();
        out.name("countries");
        out.beginArray();
        for (Country country : movie.getCountryList()){
            out.value(country.getCountryName());
        }
        out.endArray();

        out.name("reviews");
        out.beginArray();
        for (String str : movie.getReviewText()) {
            out.value(str);
        }
        out.endArray();

        out.endObject();
    }

    @Override
    public Movie read(JsonReader jsonReader) throws IOException {
        return null;
    }
}

