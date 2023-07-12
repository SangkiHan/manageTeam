package com.manageTeam.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.manageTeam.global.util.GlobalUtil;

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

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
			return;
		}
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
        
        String url = request.getRequestURL().toString();
        String queryStr = request.getQueryString();
		String method = cachingRequest.getMethod();
		String requestStr = "";
		String responseStr = "";
		
        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
            	byte[] contentBytes = cachingRequest.getContentAsByteArray();
            	requestStr = GlobalUtil.cleanString(new String(contentBytes, "UTF-8"));
            }
        }
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
        	if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
        		byte[] contentBytes = cachingResponse.getContentAsByteArray();
        		responseStr = GlobalUtil.cleanString(new String(contentBytes, "UTF-8"));
            }
        }   
        
        logging(url, queryStr, method, requestStr, responseStr, ex);
	}
	
	public void logging(String url, String queryStr, String method, String requestStr, String responseStr, Exception e) {
		StringBuilder logMessageBuilder = new StringBuilder("\n");
		if("GET".equals(method)) {
			url+="/"+queryStr;
		}
		logMessageBuilder.append("┌───────────────────────────────────────────────────────────────────────────────────────\n");
		logMessageBuilder.append("│Request URL: "+url+"\n");
		logMessageBuilder.append("│Request Method: "+method+"\n");
		if(!"".equals(requestStr)&&requestStr!=null) {
		logMessageBuilder.append("│Request Body: "+requestStr+"\n");
		}
		if(!"".equals(responseStr)&&responseStr!=null) {
		logMessageBuilder.append("│Response Body: "+responseStr.replace("\n", "")+"\n");
		}
		logMessageBuilder.append("└───────────────────────────────────────────────────────────────────────────────────────\n");
		log.info(logMessageBuilder.toString());
	}
}
