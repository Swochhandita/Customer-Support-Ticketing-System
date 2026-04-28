package com.ticketing.system.security;

import com.ticketing.system.repository.UserRepository;
import com.ticketing.system.utils.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
    public class JwtUtils {
    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(Constants.JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role ){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", role);
        return Jwts.builder()
                .claims(extraClaims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Constants.JWT_EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)//It validates the token to see whether it is real or not not to validate the user
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims ->  claims.get("role", String.class));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);// referenced method
    }

    private boolean  isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());// .before(new Date()) means "is the expiration date before right now?"
    }

    public boolean ValidToken(String token, String email) {
        final String tokenEmail = extractEmail(token);
        return (tokenEmail.equals(email) && isTokenExpired(token));
    }
}
