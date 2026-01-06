package com.ezform.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.ezform.domain.EZ_empVO;

public class HttpSessionInterceptor implements HandshakeInterceptor{

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		 if (request instanceof ServletServerHttpRequest) {
	            HttpSession session =
	                ((ServletServerHttpRequest) request)
	                .getServletRequest().getSession(false);

	            if (session != null) {
	                EZ_empVO user = (EZ_empVO) session.getAttribute("resultVO");
	                if (user != null) {
	                    attributes.put("userId", user.getEm_email()); // 또는 사번
	                }
	            }
	        }
	        return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// TODO Auto-generated method stub
		
	}

}
