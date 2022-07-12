package com.apex.app.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT token generation and validation utils
 *
 * @author Mingze Ma
 */
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private String generateToken(JwtBuilder builder) {
        return builder
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT format validation failed: {}", token);
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * Get the expiration time from the token
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * Determine if the token has expired
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * Generate token by user details
     *
     * @param userDetails User information retrieved from the database
     * @return Encoded token
     */
    public String generateToken(UserDetails userDetails) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(userDetails.getUsername());
        return generateToken(builder);
    }

    /**
     * Verify that the token is still valid
     *
     * @param token       The token passed in by the client
     * @param userDetails User information retrieved from the database
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * get username from token
     *
     * @param token The token passed in by the client
     * @return username
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String getUserIdFromToken(String token) {
        String id;
        try {
            Claims claims = getClaimsFromToken(token);
            id = claims.getId();
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

}
