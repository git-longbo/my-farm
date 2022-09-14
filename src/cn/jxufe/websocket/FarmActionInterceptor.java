package cn.jxufe.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class FarmActionInterceptor extends HttpSessionHandshakeInterceptor {
		@Override    
	    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,   
	            WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {    
	          
	        /** ����������ǿ���޸�websocketЭ�飬�������������֧�ֵ� x-webkit-deflate-frame ��չ�޸ĳ� permessage-deflate */  
	        if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")){  
	            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");  
	        }  
	        return super.beforeHandshake(request, response, wsHandler, attributes);    
	    }    
}
