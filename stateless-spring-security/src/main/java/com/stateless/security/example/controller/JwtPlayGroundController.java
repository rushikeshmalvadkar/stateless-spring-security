package com.stateless.security.example.controller;

import com.stateless.security.example.dto.LoginRequestDto;
import io.jsonwebtoken.*;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/jwt/play")
public class JwtPlayGroundController {

    public static final String SECRET_KEY = "ABHI";

    @PostMapping(value = "/accessToken")
    public String generateToken(@RequestBody LoginRequestDto loginRequestDto){

        String username = loginRequestDto.getUsername();
        String jwtToken = Jwts.builder()
                .setId("A1234")
                .setSubject(username)
                .setIssuer("ABHISHEK")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(SECRET_KEY.getBytes()))
                .compact();

        return jwtToken;


    }

    @GetMapping(value = "/{token}")
    public String validateToken(@PathVariable("token") String jwtToken){
        try {
            Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encode(SECRET_KEY.getBytes()))
                    .parseClaimsJws(jwtToken);
            return "Token is valid";
        } catch (ExpiredJwtException e) {
              return "Token is expired";
        } catch (UnsupportedJwtException e) {
            return "Invalid token";
        } catch (MalformedJwtException e) {
            return "Invalid token";
        } catch (SignatureException e) {
            return "Invalid token";
        } catch (IllegalArgumentException e) {
            return "Invalid token";
        }

    }

}
