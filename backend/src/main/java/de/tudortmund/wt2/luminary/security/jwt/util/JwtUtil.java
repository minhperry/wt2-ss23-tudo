package de.tudortmund.wt2.luminary.security.jwt.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtUtil {
    String extractUsername(String token);
    Date extractExpiration(String token);
    boolean isTokenExpired(String token);
    boolean validateToken(String token, UserDetails userDetails);
    boolean validateToken(String token);
    String generateToken(Authentication authentication);
}