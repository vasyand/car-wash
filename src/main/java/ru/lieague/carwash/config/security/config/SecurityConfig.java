package ru.lieague.carwash.config.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.lieague.carwash.config.security.jwt.JwtEmailPasswordAuthFilter;
import ru.lieague.carwash.config.security.jwt.JwtTokenFilter;
import ru.lieague.carwash.config.security.jwt.JwtTokenGenerator;

import static org.springframework.http.HttpMethod.*;
import static ru.lieague.carwash.model.UserRole.*;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenGenerator jwtTokenGenerator;

    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(
                        "/api/v1/registration",
                        "/api/v1/auth",
                        "/api/v1/recovery-password"
                )
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers(
                        PUT,
                        "/api/v1/bookings/**",
                        "/api/v1/users/{id}")
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers(POST, "/api/v1/bookings/**")
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers(
                        GET,
                        "/api/v1/bookings/{id}",
                        "/api/v1/car-wash-services/**",
                        "/api/v1/users/{id}")
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers(DELETE, "/api/v1/users/{id}")
                .hasAnyRole(USER.name(), OPERATOR.name())
                .and()
                .authorizeRequests()
                .antMatchers(GET, "/api/v1/boxes/{id}")
                .hasAnyRole(ADMIN.name(), OPERATOR.name())
                .and()
                .authorizeRequests()
                .antMatchers(PUT, "/api/v1/users/{id}/**")
                .hasAnyRole(ADMIN.name(), OPERATOR.name())
                .anyRequest()
                .hasRole(ADMIN.name())
                .and()
                .addFilterBefore(new JwtEmailPasswordAuthFilter(authenticationManagerBean(), jwtTokenGenerator), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtTokenFilter, JwtEmailPasswordAuthFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
