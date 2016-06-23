package com.dsliusar.security.handler;

import com.dsliusar.annotations.SecurityRoles;
import com.dsliusar.enums.RolesEnum;
import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.services.security.handler.SecurityRoleHandlerService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DSliusar on 22.06.2016.
 */
public class SecurityRoleHandlerTest {

    @SecurityRoles(roles = {RolesEnum.USER})
    public void testUser(SecurityRoleHandlerTest securityRoleHandlerTest) throws MovieLandSecurityException {
        SecurityRoleHandlerService securityRoleHandler = new SecurityRoleHandlerService();
        securityRoleHandler.handle(SecurityRoleHandlerTest.class, Thread.currentThread().getStackTrace()[1].getMethodName(), "USER");
    }

    @SecurityRoles(roles = {RolesEnum.ADMIN, RolesEnum.USER})
    public void testAdmin(SecurityRoleHandlerTest securityRoleHandlerTest) throws MovieLandSecurityException {

        SecurityRoleHandlerService securityRoleHandler = new SecurityRoleHandlerService();
        System.out.println(SecurityRoleHandlerService.class);
        securityRoleHandler.handle(SecurityRoleHandlerTest.class, Thread.currentThread().getStackTrace()[1].getMethodName(), "ADMIN");
    }

    @SecurityRoles(roles = {RolesEnum.ADMIN, RolesEnum.USER, RolesEnum.GUEST})
    public void testNoName(SecurityRoleHandlerTest securityRoleHandlerTest) throws MovieLandSecurityException {

        SecurityRoleHandlerService securityRoleHandler = new SecurityRoleHandlerService();
        securityRoleHandler.handle(SecurityRoleHandlerTest.class, Thread.currentThread().getStackTrace()[1].getMethodName(), "ADMa");
    }

    @Test
    public void testNoName() throws MovieLandSecurityException {
        SecurityRoleHandlerTest handlerTest = new SecurityRoleHandlerTest();
        String role = "ADMa";
        String expectedError = "Current role " + role + " is prohibited to do this operation";
        String actualError = "";
        try {
            handlerTest.testNoName(handlerTest);
        } catch (MovieLandSecurityException e) {
            actualError = e.getMessage();
        }
        Assert.assertEquals(expectedError, actualError);
    }

    @Test
    public void testAdmin() throws MovieLandSecurityException {
        SecurityRoleHandlerTest handlerTest = new SecurityRoleHandlerTest();
        String expectedError = null;
        String actualError = null;
        try {
            handlerTest.testAdmin(handlerTest);
        } catch (MovieLandSecurityException e){
            actualError = e.getMessage();
        }
        Assert.assertEquals(expectedError, actualError);
    }

    @Test
    public void testUser() throws MovieLandSecurityException {
        SecurityRoleHandlerTest handlerTest = new SecurityRoleHandlerTest();
        String expectedError = null;
        String actualError = null;
        try{
            handlerTest.testUser(handlerTest);
        }catch (MovieLandSecurityException e){
            actualError = e.getMessage();
        }
        Assert.assertEquals(expectedError,actualError);
    }

}
