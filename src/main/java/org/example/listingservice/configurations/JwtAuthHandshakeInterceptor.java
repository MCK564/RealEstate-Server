package org.example.listingservice.configurations;

import org.example.listingservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.models.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtUtils jwtUtils ;
    private  final UserDetailsService userDetailsService;


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        URI uri = request.getURI();
        String token = UriComponentsBuilder.fromUri(uri)
                .build()
                .getQueryParams()
                .getFirst("token");

        assert token != null;
        if(!token.equals(MessageKeys.UNAUTHORIZED)){
            String phoneNumber = jwtUtils.extractPhoneNumber(token);
            if(phoneNumber != null){
                User userDetails = (User) userDetailsService.loadUserByUsername(phoneNumber);
                if(jwtUtils.validateToken(token, userDetails)){
                    attributes.put("phoneNumber",phoneNumber);
                    response.setStatusCode(HttpStatusCode.valueOf(200));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
