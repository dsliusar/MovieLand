package com.dsliusar.annotations;

import com.dsliusar.enums.RolesEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityRoles {
    RolesEnum[] roles();
}
