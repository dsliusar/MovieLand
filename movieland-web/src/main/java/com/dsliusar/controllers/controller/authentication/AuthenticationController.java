package com.dsliusar.controllers.controller.authentication;

import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.services.security.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping(value = "/v1")
public class AuthenticationController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationServiceImpl;

    @RequestMapping(value = "/authorize", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> authorizeUser(@RequestBody UserCredentialsRequest userCredentialsRequest){
        LOGGER.info("Authorizing user by credentials");
        String token;
        try {
            token = authenticationServiceImpl.authenticateUser(userCredentialsRequest);
        } catch (AuthenticationException e) {
            LOGGER.error("Bad Credentials : ", e);
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Token were generated successfully, token {}",token);
        return new ResponseEntity<>(token, HttpStatus.OK) ;
    }
}
