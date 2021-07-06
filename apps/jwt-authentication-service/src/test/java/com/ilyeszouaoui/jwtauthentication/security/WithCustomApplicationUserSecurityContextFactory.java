package com.ilyeszouaoui.jwtauthentication.security;

import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUser;
import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUserWrapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;


public class WithCustomApplicationUserSecurityContextFactory implements
        WithSecurityContextFactory<WithCustomApplicationUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomApplicationUser withUser) {
        CustomApplicationUser customApplicationUser = new CustomApplicationUser(
          withUser.id(),
          withUser.email(),
          withUser.firstName(),
          withUser.lastName(),
          withUser.role()
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(
                new PreAuthenticatedAuthenticationToken(
                        new CustomApplicationUserWrapper(customApplicationUser),
                        "",
                        AuthorityUtils.createAuthorityList(customApplicationUser.getRole())
                )
        );
        return context;
    }
}
