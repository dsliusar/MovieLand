package com.dsliusar.web.controller.authentication;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.services.security.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/authorize")
public class AuthenticationController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> authorizeUser(@RequestBody UserCredentialsRequest userCredentialsRequest){
        LOGGER.info("Authorizing user by credentials, user email {}", userCredentialsRequest.getUserEmail());
        String token;
        try {
            token = authenticationService.authenticateUser(userCredentialsRequest);
        } catch (MovieLandSecurityException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Token were generated successfully, token {}",token);
        return new ResponseEntity<>(token, HttpStatus.OK) ;
    }
}
