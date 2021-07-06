package com.ilyeszouaoui.jwtauthentication.security.jwt;

import com.ilyeszouaoui.jwtauthentication.security.customuser.CustomApplicationUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtEncoderAndDecoder {
    @Value("${ilyes.jwt.secret}")
    private String jwtSecret;

    public String encodeJwtToken(int id,String email, String firstName, String lastName, String roleAsEnum) {
        String jws =  Jwts.builder()
                .claim("id",id)
                .claim("email",email)
                .claim("firstName",firstName)
                .claim("lastName",lastName)
                .claim("role",roleAsEnum)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60*60*1000)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        mapStringToByteArray(jwtSecret)
                )
                .compact();

        return jws;
    }

    public Claims decodeJwtTokenToClaims(String jws){
        return Jwts.parser()
                .setSigningKey(mapStringToByteArray(jwtSecret))
                .parseClaimsJws(jws)
                .getBody();
    }

    public CustomApplicationUser mapJwtToCustomApplicationUser(String jws){
        Claims claims = decodeJwtTokenToClaims(jws);
        return mapClaimsToCustomApplicationUser(claims);
    }

    private byte[] mapStringToByteArray(String secret){
        try{
            return secret.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private CustomApplicationUser mapClaimsToCustomApplicationUser(Claims claims){
        return new CustomApplicationUser(
                claims.get("id",Integer.class),
                claims.get("email",String.class),
                claims.get("firstName",String.class),
                claims.get("lastName",String.class),
                claims.get("role",String.class)
                );
    }
}
