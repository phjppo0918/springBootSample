package com.rptp.rptpSpringBoot.config.security;

import com.rptp.rptpSpringBoot.core.member.domain.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenProvider {
    private static final Logger logger =
            LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +
                appProperties.getAuth().getTokenExpirationMsec());
        SecretKey key = Keys.hmacShaKeyFor(appProperties.getAuth().getTokenSecret().getBytes(StandardCharsets.UTF_8));


        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(appProperties.getAuth().getTokenSecret())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.error("????????? JWT ???????????????.");
        } catch (ExpiredJwtException e) {
            logger.error("????????? JWT ???????????????.");
        } catch (UnsupportedJwtException e) {
            logger.error("???????????? ?????? JWT ???????????????.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT ????????? ?????????????????????.");
        }
        return false;
    }

}
