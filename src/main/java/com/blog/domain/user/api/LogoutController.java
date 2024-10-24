package com.blog.domain.user.api;

import com.blog.domain.user.service.TokenBlacklistService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
@RestController
public class LogoutController {
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null) {
            return ResponseEntity.badRequest().body("Token is missing");
        }

        long expirationTime = getTokenExpirationTime(token);
        tokenBlacklistService.addToBlackList(token, expirationTime);
        return ResponseEntity.ok("Logged out successfully");
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private long getTokenExpirationTime(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody();
        return claims.getExpiration().getTime() - System.currentTimeMillis();
    }


}
