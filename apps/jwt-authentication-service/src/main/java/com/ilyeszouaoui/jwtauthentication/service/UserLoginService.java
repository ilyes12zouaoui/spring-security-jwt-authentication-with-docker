package com.ilyeszouaoui.jwtauthentication.service;

import com.google.common.base.Strings;
import com.ilyeszouaoui.jwtauthentication.security.jwt.JwtEncoderAndDecoder;
import com.ilyeszouaoui.jwtauthentication.state.UserRepository;
import com.ilyeszouaoui.jwtauthentication.state.entity.UserEntity;
import com.ilyeszouaoui.jwtauthentication.to.LoginResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtEncoderAndDecoder jwtEncoderAndDecoder;

    public LoginResponseTO loginUser(String email, String password) {
        var userEntity = findUserAndVerifyCredentials(email, password);
        var jwt = jwtEncoderAndDecoder.encodeJwtToken(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getRole()
        );
        return new LoginResponseTO(jwt, null);
    }

    private UserEntity findUserAndVerifyCredentials(String email, String rawPassword) {
        if (Strings.isNullOrEmpty(email)) throw new IllegalArgumentException("email can not be null or empty !");

        var userEntity = userRepository.getByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("no user was found for the provided credentials!"));

        if (!passwordEncoder.matches(rawPassword, userEntity.getPassword()))
            throw new RuntimeException("wrong credentials!");

        return userEntity;
    }
}
