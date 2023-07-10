package com.manageTeam.global.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.global.util.AESUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			String userId = authentication.getName();
			String password = (String) authentication.getCredentials();
			
			CustumUserDetails user = userRepository.findUser(userId);
			if(user==null) {
				throw new GlobalException("존재하지 않는 회원입니다.");
			}
			
			String decPassword = AESUtil.decrypt(user.getPassword());
			
			if(!password.equals(decPassword)) {
				throw new GlobalException("비밀번호가 일치하지 않습니다.");
			}
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			return new UsernamePasswordAuthenticationToken(userId,password,authorities);
		} catch (GlobalException e) {
			throw new BadCredentialsException(e.getMessage(), e);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
