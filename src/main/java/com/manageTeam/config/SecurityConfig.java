package com.manageTeam.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
	
	private final AuthenticationSuccessHandler authenticationSuccessHandler;
	private final AuthenticationFailureHandler authenticationFailureHandler;
	private final AuthenticationEntryPoint authentictionEnctryPoint;
	private final AccessDeniedHandler accessDeniedHandler;
    
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http, Jwt jwt, TokenService tokenService) throws
		Exception {
    	
    	http
        .authorizeRequests()
            .antMatchers( "/login", "/singUp", "/access_denied", "/resources/**").permitAll()
            .antMatchers("/userAccess").hasRole("USER")
            .antMatchers("/userAccess").hasRole("ADMIN")
            .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .defaultSuccessUrl("/user_access")
            .failureUrl("/access_denied")
            .successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
            .and()
        .logout()
			.logoutSuccessUrl("/view/logoutOK")
			.invalidateHttpSession(true)
			.and()
        .csrf().disable()
    	.exceptionHandling()
			.authenticationEntryPoint(authentictionEnctryPoint)
			.accessDeniedHandler(accessDeniedHandler);

		return http.build();
	}
}
