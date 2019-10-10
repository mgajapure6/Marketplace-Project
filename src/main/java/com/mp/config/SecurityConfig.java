package com.mp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.enable-csrf}")
	private boolean csrfEnabled;
	
	@Value("${security.csrf-name}")
	private String csrfName;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (!csrfEnabled) {
			http.csrf().disable();
		}else {
			final HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();
		    tokenRepository.setHeaderName(csrfName);
			http.authorizeRequests().anyRequest().permitAll();
			http.csrf().csrfTokenRepository(tokenRepository);
			http.headers().frameOptions().sameOrigin();
		}
	}
}