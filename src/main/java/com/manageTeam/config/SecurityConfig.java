package com.manageTeam.config;

import org.springframework.beans.factory.annotation.Autowired;
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

@EnableWebSecurity        //spring security 를 적용한다는 Annotation
@RequiredArgsConstructor
public class SecurityConfig  {
    @Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private AuthenticationEntryPoint authentictionEnctryPoint;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
    
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http, Jwt jwt, TokenService tokenService) throws
		Exception {
    	
    	http
        .authorizeRequests()
            .antMatchers( "/login", "/singUp", "/access_denied", "/resources/**").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
            // USER, ADMIN 접근 허용
            .antMatchers("/userAccess").hasRole("USER")
            .antMatchers("/userAccess").hasRole("ADMIN")
            .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .defaultSuccessUrl("/user_access")
            .failureUrl("/access_denied") // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다.
            .successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
            .and()
        .logout()
			.logoutSuccessUrl("/view/logoutOK")
			.invalidateHttpSession(true)
			.and()
        .csrf().disable()		//로그인 창
    	.exceptionHandling()
			.authenticationEntryPoint(authentictionEnctryPoint)
			.accessDeniedHandler(accessDeniedHandler);

		return http.build();
	}
}
