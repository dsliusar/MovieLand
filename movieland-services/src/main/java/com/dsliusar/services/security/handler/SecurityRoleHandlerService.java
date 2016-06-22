package com.dsliusar.services.security.handler;

import com.dsliusar.annotations.SecurityRoles;
import com.dsliusar.enums.RolesEnum;
import com.dsliusar.exceptions.MovieLandSecurityException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class SecurityRoleHandlerService {
    public boolean handle(Object o, String inMethod, String role) throws MovieLandSecurityException{
        System.out.println(o.getClass());
        for (Method method : o.getClass().getDeclaredMethods()) {
            System.out.println(method.getName());
            if (inMethod.equalsIgnoreCase(method.getName())) {
                SecurityRoles roles = method.getAnnotation(SecurityRoles.class);
                    if (roles != null) {
                        for (RolesEnum rolesEnum : roles.roles()) {
                            if (rolesEnum.toString().equalsIgnoreCase(role)) {
                                return true;
                            } else
                            {
                                throw new MovieLandSecurityException("Current role " + role + " is prohibited to do this operation");
                            }
                        }
                    }
            }
        }
        return false;
    }
}

