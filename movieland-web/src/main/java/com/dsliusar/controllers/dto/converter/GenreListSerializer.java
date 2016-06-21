package com.dsliusar.controllers.dto.converter;

import com.dsliusar.persistence.entity.Genre;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * Created by Red1 on 6/17/2016.
 */
public class GenreListSerializer extends JsonSerializer<List<Genre>> {
    @Override
    public void serialize(List<Genre> genres, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray();
        for (Genre genre : genres) {
            jsonGenerator.writeString(genre.getName());
        }
        jsonGenerator.writeEndArray();
    }
}
