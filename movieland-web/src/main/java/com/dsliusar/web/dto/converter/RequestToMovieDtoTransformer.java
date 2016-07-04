package com.dsliusar.web.dto.converter;

import com.dsliusar.tools.http.entities.AllMoviesRequestDto;

public class RequestToMovieDtoTransformer {

    public static AllMoviesRequestDto convertToDto(String ratingOrder,String priceOrder, Integer pageNumber,String currency){
        return new AllMoviesRequestDto(ratingOrder,priceOrder,pageNumber,currency);
    }
}
