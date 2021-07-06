package com.ilyeszouaoui.jwtauthentication.security.jwt;

import com.google.common.base.Strings;
import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUser;
import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtSecurityFilter extends GenericFilterBean {
    private static final String BEARER = "bearer ";

    @Autowired
    private JwtEncoderAndDecoder jwtEncoderAndDecoder;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var authorizationValue = getHeaderAuthorizationValue(request);
        if (verifyAuthorizationValue(authorizationValue)) {
            CustomApplicationUser customApplicationUser = jwtEncoderAndDecoder.mapJwtToCustomApplicationUser(extractJwtValue(authorizationValue));
            SecurityContextHolder.getContext().setAuthentication(
                    new PreAuthenticatedAuthenticationToken(
                            new CustomApplicationUserWrapper(customApplicationUser),
                            "",
                            AuthorityUtils.createAuthorityList(customApplicationUser.getRole())
                            )
            );
        }
        chain.doFilter(request, response);
    }

    private String getHeaderAuthorizationValue(ServletRequest request) {
        return ((HttpServletRequest) request).getHeader("Authorization");
    }

    private boolean verifyAuthorizationValue(String authorizationValue){
       return  !Strings.isNullOrEmpty(authorizationValue) && authorizationValue.toLowerCase().startsWith(BEARER);
    }

    private String extractJwtValue(String bearerValue){

        return bearerValue.split(" ")[1].trim();
    }

}
