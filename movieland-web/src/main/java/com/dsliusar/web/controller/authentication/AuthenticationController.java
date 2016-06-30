package com.dsliusar.web.controller.authentication;

import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.tools.exceptions.MovieLandSecurityException;
import com.dsliusar.tools.http.entities.UserCredentialsRequest;
import com.dsliusar.web.dto.TokenRequestDto;
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
    public ResponseEntity<?> authorizeUser(@RequestBody UserCredentialsRequest userCredentialsRequest) throws MovieLandSecurityException {
        LOGGER.info("Authorizing user by credentials, user email {}", userCredentialsRequest.getUserEmail());
        String token = authenticationService.authenticateUser(userCredentialsRequest);
        LOGGER.info("Token were generated successfully, token {}",token);
        return new ResponseEntity<>(new TokenRequestDto(token), HttpStatus.OK) ;
    }
}
