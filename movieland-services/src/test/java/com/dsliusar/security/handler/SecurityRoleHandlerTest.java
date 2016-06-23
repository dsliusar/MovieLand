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
    public void testUser() throws MovieLandSecurityException {
        SecurityRoleHandlerService securityRoleHandler = new SecurityRoleHandlerService();
        securityRoleHandler.handle(SecurityRoleHandlerTest.class, Thread.currentThread().getStackTrace()[1].getMethodName(), "USER");
    }
    @SecurityRoles(roles = {RolesEnum.ADMIN, RolesEnum.USER})
    public void testAdmin() throws MovieLandSecurityException {

        SecurityRoleHandlerService securityRoleHandler = new SecurityRoleHandlerService();
        System.out.println(SecurityRoleHandlerService.class);
        securityRoleHandler.handle(SecurityRoleHandlerTest.class, Thread.currentThread().getStackTrace()[1].getMethodName(), "ADMIN");
    }

    @SecurityRoles(roles = {RolesEnum.ADMIN, RolesEnum.USER, RolesEnum.GUEST})
    public void testNoName() throws MovieLandSecurityException {

        SecurityRoleHandlerService securityRoleHandler = new SecurityRoleHandlerService();
        securityRoleHandler.handle(SecurityRoleHandlerTest.class, Thread.currentThread().getStackTrace()[1].getMethodName(), "ADMa");
    }

      public void testNoAnnotation() throws MovieLandSecurityException {

        SecurityRoleHandlerService securityRoleHandler = new SecurityRoleHandlerService();
        securityRoleHandler.handle(SecurityRoleHandlerTest.class, Thread.currentThread().getStackTrace()[1].getMethodName(), "ADMa");
    }

    @Test
    public void testNoNameTest(){
        SecurityRoleHandlerTest handlerTest = new SecurityRoleHandlerTest();
        String role = "ADMa";
        String expectedError = "Current role " + role + " is prohibited to do this operation";
        String actualError = "";
        try {
            handlerTest.testNoName();
        } catch (MovieLandSecurityException e) {
            actualError = e.getMessage();
        }
        Assert.assertEquals(expectedError, actualError);
    }

    @Test
    public void testAdminTest(){
        SecurityRoleHandlerTest handlerTest = new SecurityRoleHandlerTest();
        String expectedError = null;
        String actualError = null;
        try {
            handlerTest.testAdmin();
        } catch (MovieLandSecurityException e){
            actualError = e.getMessage();
        }
        Assert.assertEquals(expectedError, actualError);
    }

    @Test
    public void testUserTest(){
        SecurityRoleHandlerTest handlerTest = new SecurityRoleHandlerTest();
        String expectedError = null;
        String actualError = null;
        try{
            handlerTest.testUser();
        }catch (MovieLandSecurityException e){
            actualError = e.getMessage();
        }
        Assert.assertEquals(expectedError,actualError);
    }

    @Test
    public void testNoAnnotationTest(){
        SecurityRoleHandlerTest handlerTest = new SecurityRoleHandlerTest();
        String expectedError = "No Annotation found";
        String actualError = null;
        try{
            handlerTest.testNoAnnotation();
        }catch (MovieLandSecurityException e){
            actualError = e.getMessage();
        }
        Assert.assertEquals(expectedError,actualError);
    }

}
