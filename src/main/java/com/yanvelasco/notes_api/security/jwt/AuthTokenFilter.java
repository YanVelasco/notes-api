package com.yanvelasco.notes_api.security.jwt;

import com.yanvelasco.notes_api.security.services.UserDetailsServiceIMP;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    final JwtUtils jwtUtils;
    final UserDetailsServiceIMP userDetailsServiceIMP;

    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceIMP userDetailsServiceIMP) {
        this.jwtUtils = jwtUtils;
        this.userDetailsServiceIMP = userDetailsServiceIMP;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.debug("AuthTokenFilter called for URL: {}", request.getRequestURI());
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUsernameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsServiceIMP.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    logger.debug("Roles from jwt: {}", authentication.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.debug("Authenticated user: {}, setting security context", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error("I can't set the user authentication for you.", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest httpServletRequest) {
        String jwt = jwtUtils.getJwtFromHeader(httpServletRequest);
        logger.debug("JWT: {}", jwt);
        return jwt;
    }

}