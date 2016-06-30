package com.dsliusar.tools.enums;

public enum Roles {
    ADMIN("admin"),
    USER("user"),
    GUEST("guest");


    Roles(String s) {
        this.s = s;
    }

    private String s;

    /**
     * Get the ENUM role of the user using the String role of the user
     * @param s
     * @return
     */
    public static Enum getRole(String s){
        for(Roles roles : Roles.values()) {
            if(roles.toString().equalsIgnoreCase(s)){
                return roles;
            }
        }
        return GUEST;
    }
}
