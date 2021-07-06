package com.ilyeszouaoui.jwtauthentication.resource.app;

import com.ilyeszouaoui.jwtauthentication.service.UserLoginService;
import com.ilyeszouaoui.jwtauthentication.to.LoginRequestTO;
import com.ilyeszouaoui.jwtauthentication.to.LoginResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/v1/users")
public class UserLoginResource {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public LoginResponseTO loginUser(@RequestBody LoginRequestTO loginRequestTO){
       return userLoginService.loginUser(loginRequestTO.getEmail(),loginRequestTO.getPassword());
    }
}
