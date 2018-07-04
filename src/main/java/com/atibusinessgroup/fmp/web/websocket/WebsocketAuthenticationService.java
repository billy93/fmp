package com.atibusinessgroup.fmp.web.websocket;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.atibusinessgroup.fmp.security.AuthoritiesConstants;
import com.atibusinessgroup.fmp.security.jwt.TokenProvider;

@Service
public class WebsocketAuthenticationService {

	private final TokenProvider tokenProvider;
	
	public WebsocketAuthenticationService(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	public Principal authenticateWebsocketToken(StompHeaderAccessor accessor) {
		String bearerToken = accessor.getFirstNativeHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7, bearerToken.length());
            if (tokenProvider.validateToken(token)) {
            	return tokenProvider.getAuthentication(token);
            }
        }
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS));
        return new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous", authorities);
	}
}
