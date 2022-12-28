package com.example.sns_project.config;

import com.example.sns_project.domain.entity.User;
import com.example.sns_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private  final UserService userService;
    @Value("${jwt.secret}")
    private String key;


    //인가
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        return  httpSecurity
                .httpBasic().disable() //ui쪽 disable
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/v1/users/join","/api/v1/users/login").permitAll()
                //.antMatchers(HttpMethod.POST,"/api/v1/posts/*", "/api/v1/posts").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt사용하는 겨우 씀
                .and()
                .addFilterBefore(new JwtFilter(userService,key), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
