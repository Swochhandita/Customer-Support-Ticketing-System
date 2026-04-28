package com.ticketing.system.security;

import com.ticketing.system.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j // for logger
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(Constants.JWT_HEADER);
        if(authHeader == null || !authHeader.startsWith(Constants.JWT_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authHeader.substring(Constants.JWT_PREFIX.length());
        String email = null;
        try{
            email = jwtUtils.extractEmail(jwt);
        } catch(Exception e){
            log.warn("Could not extract email from the JWT token: {}", e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }
        if(email == null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails= userDetailsService.loadUserByUsername(email);
            if(jwtUtils.ValidToken(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.debug("Authenticated User: {}", email);
            }
        }
        filterChain.doFilter(request, response);// always pass the request otherwise the request will stuck forever
    }
}
