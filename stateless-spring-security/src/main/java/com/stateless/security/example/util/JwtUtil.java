package com.stateless.security.example.util;

import com.stateless.security.example.dto.JpaUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt-expiry-time-in-ms}")
    private int jwtExpireTimeInMs;

    @Value("${app.jwt-secret-key")
    private String jwtSecretKey;

    public String generateToken(Authentication authentication){
        JpaUserDetails jpaUserDetails = (JpaUserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(jpaUserDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpireTimeInMs))
                .signWith(SignatureAlgorithm.HS256 , Base64.getEncoder().encode(jwtSecretKey.getBytes()))
                .compact();
    }


    public boolean validateToken(String token , String username){
        String usernameInToken = getUsername(token);
        return (usernameInToken.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDate(String token) {
        return getClaims(token)
                .getExpiration();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(jwtSecretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }


}
