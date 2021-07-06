package com.ilyeszouaoui.jwtauthentication.security.customuser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomApplicationUserWrapper implements UserDetails {

    private CustomApplicationUser customApplicationUser;

    public CustomApplicationUserWrapper(CustomApplicationUser customApplicationUser) {
        this.customApplicationUser = customApplicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public CustomApplicationUser getCustomApplicationUser() {
        return customApplicationUser;
    }

    public void setCustomApplicationUser(CustomApplicationUser customApplicationUser) {
        this.customApplicationUser = customApplicationUser;
    }
}
