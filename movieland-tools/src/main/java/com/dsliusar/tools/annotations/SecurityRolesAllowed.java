package com.dsliusar.tools.annotations;

import com.dsliusar.tools.enums.Roles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityRolesAllowed {
    Roles[] roles();
}
