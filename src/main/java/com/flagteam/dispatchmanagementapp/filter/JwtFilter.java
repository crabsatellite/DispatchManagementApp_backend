package com.flagteam.dispatchmanagementapp.filter;


import com.flagteam.dispatchmanagementapp.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        // Get header
        final String authorizationHeader = httpServletRequest.getHeader(HEADER);

        // Get jwt token
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX)) {
            jwt = authorizationHeader.substring(PREFIX.length());
        }

        if (jwt != null && jwtUtil.validateToken(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {

            String username = jwtUtil.extractUsername(jwt);
            List<GrantedAuthority> grantedAuthorities = Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority(USER)});
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username, null, grantedAuthorities);
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private static final String USER = "user";
    private final JwtUtil jwtUtil;

}

