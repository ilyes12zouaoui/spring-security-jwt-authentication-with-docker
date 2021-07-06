package com.ilyeszouaoui.jwtauthentication.security;

import com.ilyeszouaoui.jwtauthentication.security.customuser.RoleConstants;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = WithCustomApplicationUserSecurityContextFactory.class)
public @interface WithCustomApplicationUser {

    int id() default 1432;

    String role() default RoleConstants.ROLE_SIMPLE;

    String email() default "email@test";

    String firstName() default "ilyes";

    String lastName() default "zouaoui";
}