package com.ilyeszouaoui.jwtauthentication.resource.internal;

import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUser;
import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUserWrapper;
import com.ilyeszouaoui.jwtauthentication.security.customuser.RoleConstants;
import com.ilyeszouaoui.jwtauthentication.service.UserProfileService;
import com.ilyeszouaoui.jwtauthentication.to.UpdateUserRequestTO;
import com.ilyeszouaoui.jwtauthentication.to.UpdateUserResponseTO;
import com.ilyeszouaoui.jwtauthentication.to.UserProfileResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/v1/users")
public class UserProfileInternalResource {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public UserProfileResponseTO getProfile(@PathVariable int id) {
        CustomApplicationUser customApplicationUser = new CustomApplicationUser();
        customApplicationUser.setRole(RoleConstants.ROLE_INTERNAL);
        return userProfileService.getUserProfile(id, customApplicationUser);
    }

    @PutMapping("/{id}")
    public UpdateUserResponseTO updateProfile(@PathVariable int id, @RequestBody UpdateUserRequestTO updateUserRequestTO) {
        CustomApplicationUser customApplicationUser = new CustomApplicationUser();
        customApplicationUser.setRole(RoleConstants.ROLE_INTERNAL);
        return userProfileService.updateUserProfile(id, customApplicationUser, updateUserRequestTO.getFirstName(), updateUserRequestTO.getLastName());
    }
}
