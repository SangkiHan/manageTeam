package com.manageTeam.global.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

/**
 * @descreption: Spring Security 사용자 Custom Dto
 * @author skhan
 * */
@Setter
@Getter
public class CustumUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String role;
	private String enale;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.role));
	}

	@Override
	public String getPassword() {
		return this.username;
	}

	@Override
	public String getUsername() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return ("YES".equals(enale)) ? true : false;
	}

}
