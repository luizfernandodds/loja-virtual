package com.dev.loja_virtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  implements HttpSessionIdListener {


    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers(HttpMethod.GET, "/salvarAcesso", "/deleteAcesso")
	    		.requestMatchers(HttpMethod.POST, "/salvarAcesso", "/deleteAcesso");
	}

	@Override
	public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
		// TODO Auto-generated method stub
		
	}

}
