package com.ilyeszouaoui.jwtauthentication.resource.app;

import com.ilyeszouaoui.jwtauthentication.to.RegisterUserRequestTO;
import com.ilyeszouaoui.jwtauthentication.service.UserRegisterService;
import com.ilyeszouaoui.jwtauthentication.to.RegisterUserResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app/v1/users")
public class UserRegisterResource {

    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("/register")
    public RegisterUserResponseTO registerSimpleUser(@RequestBody RegisterUserRequestTO registerUserRequestTO) {
      return  userRegisterService.registerSimpleUser(
                registerUserRequestTO.getEmail(),
                registerUserRequestTO.getFirstName(),
                registerUserRequestTO.getFirstName(),
                registerUserRequestTO.getPassword()
        );
    }
}
