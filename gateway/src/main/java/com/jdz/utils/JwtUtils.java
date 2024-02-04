package com.jdz.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtils {
    private final String SECRET;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        SECRET = secret;
    }

    public void validateToken(final String toke) {
        Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(toke);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
