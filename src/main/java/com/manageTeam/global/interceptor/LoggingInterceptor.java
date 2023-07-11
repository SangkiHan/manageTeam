package com.manageTeam.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @description : 요청 응답값 로깅을 위한 Interceptor
 * @author skhan
 * */
@Log4j2
@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor{
	
	private final ObjectMapper objectMapper;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		StringBuilder logMessageBuilder = new StringBuilder("\n------------------------------------------------------------------------------------------\n");
		
		if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
			return;
		}
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

		String method = cachingRequest.getMethod();
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		logMessageBuilder.append("|Request URL : "+url+"/"+queryString+"\n");
		logMessageBuilder.append("|Request Method : "+method+"\n");
        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
            	logMessageBuilder.append("Request Body : "+objectMapper.readTree(cachingRequest.getContentAsByteArray())+"\n");
            }
        }
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
        	if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
        		logMessageBuilder.append("|Response Body : "+objectMapper.readTree(cachingResponse.getContentAsByteArray())+"\n");
            }
        }   
        logMessageBuilder.append("------------------------------------------------------------------------------------------\n");
        log.info(logMessageBuilder.toString());
	}
}
