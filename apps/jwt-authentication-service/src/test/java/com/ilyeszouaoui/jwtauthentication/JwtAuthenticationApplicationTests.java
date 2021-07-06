package com.ilyeszouaoui.jwtauthentication;

import com.ilyeszouaoui.jwtauthentication.security.jwt.JwtEncoderAndDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class JwtAuthenticationApplicationTests {

	@Test
	void contextLoads() {
	}

}
