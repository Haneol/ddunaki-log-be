package com.blog.domain.user.security.filter;

import com.blog.domain.user.service.JwtService;
import com.blog.domain.user.service.TokenBlacklistService;
import lombok.extern.log4j.Log4j2;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


import java.io.IOException;
import java.util.Arrays;

@Component
@Log4j2
public class JwtAuthFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private TokenBlacklistService tokenBlacklistService;
    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService, HandlerExceptionResolver handlerExceptionResolver, TokenBlacklistService tokenBlacklistService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        log.info("check url" + path);

        if (path.startsWith("/api/user/")) {
            return true;
        }
        if (path.startsWith("/api/")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("################do filter##################");
        log.info("################do filter##################");
        log.info("################do filter##################");
        final String authHeader = request.getHeader("Authorization");
        try {
            String token = extractToken(request);

            if (token != null && tokenBlacklistService.isTokenBlacklisted(token)) {
                log.info("Token is blacklisted");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token is blacklisted");
                return;
            }

            if (authHeader == null) {
                log.info("authHeader is null");
                filterChain.doFilter(request, response);
                return;
            }
            if (!authHeader.startsWith("Bearer ")) {
                log.info("authheader isn't start with Bearer");
                filterChain.doFilter(request, response);
                return;
            }
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null ,exception);
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
