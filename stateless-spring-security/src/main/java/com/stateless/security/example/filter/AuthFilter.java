package com.stateless.security.example.filter;

import com.stateless.security.example.config.SecurityConfig;
import com.stateless.security.example.service.JpaUserDetailsService;
import com.stateless.security.example.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final JpaUserDetailsService userDetailsService;


    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            log.info("<<<< AuthFilter");

            // Extract accessToken from request header
            String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (bearerToken != null){
                String accessToken = bearerToken.split(" ")[1];
                if (accessToken != null){
                    String username = jwtUtil.getUsername(accessToken);
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    boolean isTokenValid = jwtUtil.validateToken(accessToken, userDetails.getUsername());

                    if (isTokenValid){
                        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

            filterChain.doFilter(request,response);
            log.info("AuthFilter >>>>");
        } catch (JwtException ex) {
            log.info("Delegating to global exception handler from AuthFilter");
            handlerExceptionResolver.resolveException(request , response , null,ex);
        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(SecurityConfig.PUBLIC_URLS)
                        .anyMatch(url -> new AntPathRequestMatcher(url).matches(request));
    }
}
