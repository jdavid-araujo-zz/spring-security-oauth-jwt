package com.david.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfigInMemory extends WebSecurityConfigurerAdapter {

	 private OAuthSecurityConfig oAuthSecurityConfig;
	 
	 
	 @Autowired
	 public WebSecurityConfigInMemory(OAuthSecurityConfig oAuthSecurityConfig) {
		this.oAuthSecurityConfig= oAuthSecurityConfig;
	}

	@Autowired
	    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		  .withUser("user").password(oAuthSecurityConfig.passwordEncoder().encode("user")).roles("USER").and()
		  .withUser("admin").password(oAuthSecurityConfig.passwordEncoder().encode("admin")).roles("ADMIN");
	    }

	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    protected void configure(final HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.anyRequest().permitAll()
			.and().csrf().disable();
	    }   
}
