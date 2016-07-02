package com.dsliusar.web.controller.exceptions;

import com.dsliusar.tools.exceptions.MovieLandSecurityException;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.exceptions.RequestException;
import com.dsliusar.tools.exceptions.UrlRequestException;
import com.dsliusar.web.dto.ExceptionResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerExcpetionHandling {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(Exception e) {
        LOGGER.info("Exception occurred : ",e);
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieLandSecurityException.class)
    public ResponseEntity<ExceptionResponseDto> movieLandSecurityExceptionHandler(MovieLandSecurityException e) {
        LOGGER.info("Security Exception occurred : ",e);
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> notFoundExceptionHandler(NotFoundException e) {
        LOGGER.info("Not Found Exception occurred : ",e);
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ExceptionResponseDto> requestFormatExceptionHandler(RequestException e) {
        LOGGER.info("The request was not correct : ",e);
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlRequestException.class)
    public ResponseEntity<ExceptionResponseDto> requestUrlFormatExceptionHandler(UrlRequestException e) {
        LOGGER.info("The URL request was not correct : ",e);
        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()), HttpStatus.I_AM_A_TEAPOT);
    }

}
