package com.manageTeam.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		CustumUserDetails user = userRepository.findUser(userId);
		if(user==null) {
			throw new GlobalException("LOG0001", "존재하지 않는 회원입니다.");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new UsernamePasswordAuthenticationToken(userId,password,authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
