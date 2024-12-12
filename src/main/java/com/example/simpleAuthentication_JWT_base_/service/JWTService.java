package com.example.simpleAuthentication_JWT_base_.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.*;
import io.jsonwebtoken.security.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.security.*;
import java.util.*;
import java.util.function.*;

@Component
public class JWTService {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long validityInMilliseconds = 1000*60*60; // 1 hour



    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }



    public boolean validateToken(String token) {

        try {
            // Use the parserBuilder to build a parser and validate the token
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Handle invalid token
            return false;
        }
//    }

//        try {
//            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
    }



    public String getUsername(String token) {
        System.out.println("inside jwtservice in authorization server token is "+ token);


        // Create a signing key from the secret
        Key signingKey = Keys.hmacShaKeyFor(SECRET.getBytes());

        // Parse the JWT and get the subject (username)
        String username = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println("inside jwtservice in authorization server username is "+username);
        return username;
//        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }


//    public void validateToken(final String token) {
//        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//    }


//    public String generateToken(String userName){
//        Map<String,Object> claims=new HashMap<>();
//        return createToken(claims,userName);
//    }
//
//    private String createToken(Map<String, Object> claims, String userName) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userName)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//    }

//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}
