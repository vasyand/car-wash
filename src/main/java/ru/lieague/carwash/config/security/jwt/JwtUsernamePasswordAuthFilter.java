package ru.lieague.carwash.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import ru.lieague.carwash.config.security.UsernamePasswordAuthDto;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.lieague.carwash.Constants.*;


public class JwtUsernamePasswordAuthFilter extends AbstractAuthenticationProcessingFilter {
    private final JwtTokenGenerator jwtTokenGenerator;

    private final AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthFilter(AuthenticationManager authenticationManager, JwtTokenGenerator jwtTokenGenerator) {
        super("/api/v1/auth");
        this.authenticationManager = authenticationManager;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

            UsernamePasswordAuthDto authRequest = parseToUsernamePasswordRequest(request);
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            );
            return authenticationManager.authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        String accessToken = jwtTokenGenerator.generateAccessToken(authResult);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(authResult);

        response.addHeader(AUTHORIZATION_HEADER, BEARER_PARAMETER + accessToken);
        response.addHeader(REFRESH_TOKEN_HEADER, BEARER_PARAMETER + refreshToken);
    }

    private UsernamePasswordAuthDto parseToUsernamePasswordRequest(HttpServletRequest request) {
        try {
            return new ObjectMapper()
                    .readValue(request.getInputStream(), UsernamePasswordAuthDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
