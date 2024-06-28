package com.fulinx.spring.web.framework.config;

import com.fulinx.spring.web.framework.security.AuthenticationFilter;
import com.fulinx.spring.web.framework.security.WebAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Slf4j
public class WebSecurityConfig {

    private final WebAuthenticationProvider webAuthenticationProvider;

    public WebSecurityConfig(WebAuthenticationProvider webAuthenticationProvider) {
        this.webAuthenticationProvider = webAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationFilter authenticationFilter) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(requests ->
                        {
                            try {
                                requests.requestMatchers(
                                                "/swagger/**",
                                                "/swagger-ui/",
                                                "/webjars/**",
                                                "/v3/**",
                                                "/v2/**",
                                                "/swagger-resources/**",
                                                "/doc.html").permitAll()
                                        .requestMatchers(
                                                HttpMethod.GET,
                                                "/",
                                                "/*.html",
                                                "/favicon.ico",
                                                "/**/*.html",
                                                "/**/*.css",
                                                "/**/*.js"
                                        ).permitAll()
                                        .requestMatchers("/share/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/server-side/system-user/authentication/login").permitAll()
                                        .anyRequest().authenticated();
                            } catch (Exception e) {
                                log.error("Error configuring HTTP security", e);
                            }
                        }
                )
                .authenticationProvider(webAuthenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
