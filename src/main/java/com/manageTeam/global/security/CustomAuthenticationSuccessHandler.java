package com.manageTeam.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manageTeam.domain.user.dto.UserResponse;
import com.manageTeam.domain.user.service.UserReadService;
import com.manageTeam.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @descreption: Spring Security 로그인 성공 시 CustomHandler
 * @author skhan
 * */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private final UserService userService;
	private final UserReadService userReadService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
       
		String userId = authentication.getName();
		UserResponse.Info userInfo = userReadService.findUserInfo(userId);

		response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper= new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(userInfo);
        response.getWriter().write(jsonBody);
	}

}
