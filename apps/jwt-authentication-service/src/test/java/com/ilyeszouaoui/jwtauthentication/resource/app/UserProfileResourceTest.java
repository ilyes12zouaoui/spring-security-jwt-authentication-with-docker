package com.ilyeszouaoui.jwtauthentication.resource.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilyeszouaoui.jwtauthentication.security.WithCustomApplicationUser;
import com.ilyeszouaoui.jwtauthentication.security.customuser.RoleConstants;
import com.ilyeszouaoui.jwtauthentication.to.UserProfileResponseTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithCustomApplicationUser(id = 1,role = RoleConstants.ROLE_ADMIN,email = "admin@email.test",firstName = "ilyes",lastName = "zouaoui")
    public void getProfile_correctInput_succeed() throws Exception {
        //given
        var expectedProfileResponse = new UserProfileResponseTO(
                1,
                "admin@email.test",
                "ilyes",
                "zouaoui",
                RoleConstants.ROLE_ADMIN
        );

        //when
        var response = mockMvc.perform(
                get("/api/app/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        var userProfileResponseTO = objectMapper.readValue(response.getResponse().getContentAsString(), UserProfileResponseTO.class);

        //then
        assertThat(userProfileResponseTO).isEqualToComparingFieldByFieldRecursively(expectedProfileResponse);
    }

}
