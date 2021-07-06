package com.ilyeszouaoui.jwtauthentication.service;

import com.google.common.base.Strings;
import com.ilyeszouaoui.jwtauthentication.security.customuser.RoleConstants;
import com.ilyeszouaoui.jwtauthentication.state.UserRepository;
import com.ilyeszouaoui.jwtauthentication.state.entity.UserEntity;
import com.ilyeszouaoui.jwtauthentication.to.RegisterUserResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterUserResponseTO registerSimpleUser(String email, String firstName, String lastName, String password){
        if(Strings.isNullOrEmpty(email)) throw new IllegalArgumentException("email can not be null or empty !");

        var encodedPassword = passwordEncoder.encode(password);
        var userEntity = new UserEntity(email,firstName,lastName,encodedPassword, RoleConstants.ROLE_SIMPLE);
        userEntity = userRepository.saveAndFlush(userEntity);
        return new RegisterUserResponseTO(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getRole()
        );
     }
}
