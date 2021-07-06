package com.ilyeszouaoui.jwtauthentication.resource.app;

import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUserWrapper;
import com.ilyeszouaoui.jwtauthentication.service.UserProfileService;
import com.ilyeszouaoui.jwtauthentication.to.UpdateUserRequestTO;
import com.ilyeszouaoui.jwtauthentication.to.UpdateUserResponseTO;
import com.ilyeszouaoui.jwtauthentication.to.UserProfileResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app/v1/users")
public class UserProfileResource {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public UserProfileResponseTO getProfile(@PathVariable int id, @AuthenticationPrincipal CustomApplicationUserWrapper customApplicationUserWrapper){
        return userProfileService.getUserProfile(id,customApplicationUserWrapper.getCustomApplicationUser());
    }

    @PutMapping("/{id}")
    public UpdateUserResponseTO updateProfile(@PathVariable int id, @RequestBody UpdateUserRequestTO updateUserRequestTO, @AuthenticationPrincipal CustomApplicationUserWrapper customApplicationUserWrapper){
        return userProfileService.updateUserProfile(id,customApplicationUserWrapper.getCustomApplicationUser(), updateUserRequestTO.getFirstName(), updateUserRequestTO.getLastName());
    }
}
