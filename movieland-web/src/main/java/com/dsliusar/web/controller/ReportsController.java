package com.dsliusar.web.controller;

import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.service.ReportGenerationService;
import com.dsliusar.tools.annotations.SecurityRolesAllowed;
import com.dsliusar.tools.entities.http.UserSecureTokenEntity;
import com.dsliusar.tools.enums.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAsync
@RequestMapping(value = "/v1")
public class ReportsController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Autowired
    private AuthenticationService authenticationService;


    @SecurityRolesAllowed(roles = Roles.ADMIN)
    @RequestMapping(value = "/report",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserMovieRating(@RequestParam(value = "reportType", required = false) String reportType,
                                                @RequestHeader(value = "security-token") String token) {
        LOGGER.info("Received request for report generation with type {}", reportType);
        UserSecureTokenEntity userSecure =  authenticationService.getUserByToken(token);
        reportGenerationService.generateAllSiteMovieReport(userSecure.getUserName());
        return new ResponseEntity<>("Report request generation sent", HttpStatus.OK);
    }
}