package com.manageTeam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	http
		        .authorizeRequests()
			        .antMatchers("/","/login", "/view/login").permitAll() // 루트 경로와 로그인 페이지는 모두 접근 허용
		            .anyRequest().authenticated(); // 나머지 요청은 인증 필요
	    	http
		        .formLogin()
			        .usernameParameter("id")
					.passwordParameter("password")
		            .loginPage("/view/login")	
		            .loginProcessingUrl("/login");
	    	http
	        	.logout()
			        .logoutUrl("/logout") // 로그아웃 URL 경로
		            .logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 경로
		            .permitAll(); // 로그아웃은 모두 접근 허용

		return http.build();
	}
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
    	return new BCryptPasswordEncoder();
    }
    
}
