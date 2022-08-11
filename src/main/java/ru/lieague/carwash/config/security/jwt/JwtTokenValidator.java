package ru.lieague.carwash.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.lieague.carwash.Constants.BEARER_PARAMETER;


@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

    private final UserDetailsService userDetailsService;
    @Value("${secret-key}")
    private String secretKey;


    public Authentication extractAuthentication(String authorizationHeader) {
        validateHeader(authorizationHeader);
        try {
            Claims claims = extractClaims(authorizationHeader);
            String email = getUsername(claims);
            Set<SimpleGrantedAuthority> authorities = getAuthorities(claims);
            return new UsernamePasswordAuthenticationToken(
                    userDetailsService.loadUserByUsername(email),
                    null,
                    authorities
            );
        } catch (JwtException e) {
            System.out.println(e.getMessage());
            throw new MalformedJwtException(String.format("Can not authorize token: %s", authorizationHeader));
        }

    }

    private void validateHeader(String authorizationHeader) {
        if (!authorizationHeader.startsWith(BEARER_PARAMETER)) {
            throw new MalformedJwtException(String.format("Invalid token format: %s", authorizationHeader));
        }
    }

    private String getUsername(Claims claims) {
        return claims.getSubject();
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        var auth = (List<Map<String, String>>) claims.get("auth");

        return auth.stream()
                .map(map -> new SimpleGrantedAuthority(map.get("authority")))
                .collect(Collectors.toSet());

    }

    public Claims extractClaims(String authorizationHeader) {
        validateHeader(authorizationHeader);
        String token = extractToken(authorizationHeader);
        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder();
        jwtParserBuilder.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()));
        Jws<Claims> jws = jwtParserBuilder.build().parseClaimsJws(token);
        Claims claims = jws.getBody();

        if (claims
                .getExpiration()
                .before(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
        ) {
            throw new ExpiredJwtException(jws.getHeader(), jws.getBody(), "JWT token is expired");
        }
        return claims;
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.replace(BEARER_PARAMETER, "");
    }
}
