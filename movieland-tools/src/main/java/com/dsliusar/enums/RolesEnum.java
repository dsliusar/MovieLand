package com.dsliusar.enums;

import com.dsliusar.exceptions.MovieLandSecurityException;

/**
 * Created by Red1 on 6/21/2016.
 */
public enum RolesEnum {
    ADMIN("admin"),
    USER("user"),
    MODERATOR("moderator"),
    GUEST("guest");

    RolesEnum(String s) {
        this.s = s;
    }

    private String s;

    public static boolean validateRole(String role) throws MovieLandSecurityException {
        if (role == null) {
            throw new MovieLandSecurityException("Role was not found" + role);
        }
        for (RolesEnum rolesValues : RolesEnum.values()) {
            if (role.equalsIgnoreCase(rolesValues.toString())) {
                return true;
            }
        }
        throw new MovieLandSecurityException("Not a valid role = " + role);
    }


}
