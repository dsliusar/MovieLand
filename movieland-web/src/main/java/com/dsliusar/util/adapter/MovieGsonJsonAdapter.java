package com.dsliusar.util.adapter;

import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Movie;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class MovieGsonJsonAdapter extends TypeAdapter<Movie> {
    @Override
    public void write(JsonWriter out, final Movie movie) throws IOException {
        out.beginObject();
        //out.beginArray();
        out.name("movieNameRus").value(movie.getMovieNameRus());
        out.name("movieNameEng").value(movie.getMovieNameeEng());
        out.name("year").value(movie.getYear());
        out.name("rating").value(movie.getRating());

        out.name("genres");
        out.beginArray();
        for (Genre genre : movie.getGenreList()){
            out.value(genre.getDescprtion());
        }
        out.endArray();
        out.endObject();
    }

    @Override
    public Movie read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
