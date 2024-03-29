package com.khaoula.acheteurservice;

import com.khaoula.acheteurservice.filters.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@Service
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(req ->
                req.requestMatchers(antMatcher("/h2-/**/console/**")).permitAll()
                        .requestMatchers(antMatcher("/swagger-ui/index.html")).permitAll()
                        .requestMatchers(antMatcher("/v2/api-docs")).permitAll()
                        .requestMatchers(antMatcher("/webjars/**")).permitAll()
                        .requestMatchers(antMatcher("/swagger-resources/**")).permitAll()
                        .requestMatchers(antMatcher("/graphiql")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET,"/acheteurs/**")).hasAuthority("ADMIN")
                        .anyRequest().authenticated());
        http.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
