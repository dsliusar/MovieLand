package com.dsliusar.dto.converter;

import com.dsliusar.entity.Country;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class CountryListSerializer extends JsonSerializer<List<Country>> {
    @Override
    public void serialize(List<Country> countries, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray();
        for (Country country : countries) {
            jsonGenerator.writeString(country.getCountryName());
        }
        jsonGenerator.writeEndArray();
    }
}

