package com.dsliusar.enums;

import com.dsliusar.exceptions.IllegalRoleException;

/**
 * Created by Red1 on 6/21/2016.
 */
public enum RolesEnum {
    ADMIN("admin"),
    USER("user"),
    MODERATOR("moderator");

    RolesEnum(String s) {
        this.s = s;
    }

    private String s;

    public static boolean validateRole(String role) throws IllegalRoleException {
        if (role == null) {
            throw new IllegalRoleException("Role was not found" + role);
        }
        for (RolesEnum rolesValues : RolesEnum.values()) {
            if (role.equalsIgnoreCase(rolesValues.toString())) {
                return true;
            }
        }
        throw new IllegalRoleException("Not a valid role = " + role);
    }


}
