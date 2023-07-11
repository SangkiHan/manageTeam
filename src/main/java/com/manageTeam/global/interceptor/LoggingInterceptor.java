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
		if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
			return;
		}
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
        
        String url = request.getRequestURL().toString()+"/"+request.getQueryString();
		String method = cachingRequest.getMethod();
		String requestStr = "";
		String responseStr = "";
		
        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
            	requestStr = objectMapper.readTree(cachingRequest.getContentAsByteArray()).textValue();
            }
        }
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
        	if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
        		responseStr = objectMapper.readTree(cachingResponse.getContentAsByteArray()).textValue();
            }
        }   
        
        logging(url, method, requestStr, responseStr);
	}
	
	public void logging(String url, String method, String requestStr, String responseStr) {
		StringBuilder logMessageBuilder = new StringBuilder("\n");
		logMessageBuilder.append("┌───────────────────────────────────────────────────────────────────────────────────────\n");
		logMessageBuilder.append("│Request URL: "+url+"\n");
		logMessageBuilder.append("│Request Method: "+method+"\n");
		logMessageBuilder.append("│Request Body: "+requestStr);
		logMessageBuilder.append("│Response Body: "+responseStr);
		logMessageBuilder.append("└───────────────────────────────────────────────────────────────────────────────────────\n");
		log.info(logMessageBuilder.toString());
	}
}
