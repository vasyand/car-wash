package ru.lieague.carwash.config.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.lieague.carwash.config.security.jwt.JwtTokenFilter;
import ru.lieague.carwash.config.security.jwt.JwtTokenGenerator;


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
                .cors().disable()
                .csrf().disable();

//        http
//                .authorizeRequests()
//                .antMatchers("/api/v1/registration", "/api/v1/auth").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,
//                        "/api/v1/users/**",
//                        "/api/v1/projects/**",
//                        "/api/v1/comments/**",
//                        "/api/v1/refresh-token")
//                .hasAnyRole(USER.name(), ADMIN.name())
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/v1/tasks/**")
//                .hasAnyRole(USER.name(), ADMIN.name())
//                .anyRequest()
//                .hasRole(ADMIN.name())
//                .and()
//                .addFilterBefore(new JwtUsernamePasswordAuthFilter(authenticationManagerBean(), jwtTokenGenerator), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(jwtTokenFilter, JwtUsernamePasswordAuthFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
