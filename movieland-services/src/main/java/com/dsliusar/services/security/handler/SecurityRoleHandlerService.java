package com.dsliusar.services.security.handler;

import com.dsliusar.annotations.SecurityRoles;
import com.dsliusar.enums.RolesEnum;
import com.dsliusar.exceptions.MovieLandSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
/**
 * Perform recursion for annotation of Roles
 * If method is annotated with roles it will not throw any errors
 * If method is not annotated or incoming role is not as in annotation throw exceptions
 *
 */
@Service
public class SecurityRoleHandlerService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public void handle(Class<?> inClass, String inMethod, String role) throws MovieLandSecurityException {
        try {
            Class<?> myClass = Class.forName(inClass.getName());
            outerLabel:
            for (Method method : myClass.getDeclaredMethods()) {
                if (inMethod.equalsIgnoreCase(method.getName())) {
                    SecurityRoles roles = method.getAnnotation(SecurityRoles.class);
                    if (roles != null) {
                        for (RolesEnum rolesEnum : roles.roles()) {
                            if (rolesEnum.toString().equalsIgnoreCase(role)) {
                                break outerLabel;
                            } else {
                                throw new MovieLandSecurityException("Current role " + role + " is prohibited to do this operation");
                            }
                        }
                    } else {
                        throw new MovieLandSecurityException("No Annotation found");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }
}

