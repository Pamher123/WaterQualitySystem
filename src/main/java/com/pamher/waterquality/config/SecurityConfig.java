package com.pamher.waterquality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // 允许跨域
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/login", "/users/register", "/users/update", "/**","devices/**").permitAll()
                        // 放行登录、注册和 OPTIONS 请求
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated() // 其他请求需要认证
                )
                .httpBasic(Customizer.withDefaults()); // 使用 HTTP Basic 认证
        return http.build();
    }
}
