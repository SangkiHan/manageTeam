package com.manageTeam.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.manageTeam.global.interceptor.LoggingInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{

	private final LoggingInterceptor loggingInterceptor;

	/**
	 * 이미지 파일을 불러오는 것은 interceptor로 잡을 필요가 없기 때문에 제외해준다.
	 * */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/vendor/**", "/css/*", "/img/*");
	}
	
}
