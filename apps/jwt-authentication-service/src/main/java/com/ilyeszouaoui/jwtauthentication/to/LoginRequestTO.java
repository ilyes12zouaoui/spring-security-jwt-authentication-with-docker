package com.ilyeszouaoui.jwtauthentication.to;

public class LoginRequestTO {

    private String email;
    private String password;

    public LoginRequestTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequestTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
