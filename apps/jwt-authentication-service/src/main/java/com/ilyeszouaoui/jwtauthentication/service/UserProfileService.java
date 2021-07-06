package com.ilyeszouaoui.jwtauthentication.service;

import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUser;
import com.ilyeszouaoui.jwtauthentication.security.customuser.RoleConstants;
import com.ilyeszouaoui.jwtauthentication.state.UserRepository;
import com.ilyeszouaoui.jwtauthentication.state.entity.UserEntity;
import com.ilyeszouaoui.jwtauthentication.to.UpdateUserResponseTO;
import com.ilyeszouaoui.jwtauthentication.to.UserProfileResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    public UserProfileResponseTO getUserProfile(int id, CustomApplicationUser customApplicationUser) {
        if (id != customApplicationUser.getId() && customApplicationUser.getRole().equalsIgnoreCase(RoleConstants.ROLE_SIMPLE))throw new RuntimeException("Not allowed to see other users profile!");
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return new UserProfileResponseTO(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getRole()
        );
    }

    public UpdateUserResponseTO updateUserProfile(int id, CustomApplicationUser customApplicationUser, String firstName, String lastName) {
        if (id != customApplicationUser.getId() && customApplicationUser.getRole().equalsIgnoreCase(RoleConstants.ROLE_SIMPLE)) throw new RuntimeException("Not allowed to update other users profile!");
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userRepository.save(userEntity);
        return new UpdateUserResponseTO(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getRole()
        );
    }
}
