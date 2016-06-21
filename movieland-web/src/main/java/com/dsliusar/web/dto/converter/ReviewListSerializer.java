package com.dsliusar.web.dto.converter;

import com.dsliusar.persistence.entity.Review;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;


public class ReviewListSerializer extends JsonSerializer<List<Review>> {
    @Override
    public void serialize(List<Review> reviews, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray();
        for (Review review : reviews) {
            jsonGenerator.writeString(review.getReviewText());
        }
        jsonGenerator.writeEndArray();
    }
}
