package com.david.resourceserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.david.resourceserver.config.token.CustomAccessTokenConverter;

@Configuration
@EnableResourceServer
public class ResourceServeConfig  extends ResourceServerConfigurerAdapter{

    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	.csrf().disable();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true).tokenServices(tokenServices());
	}
	
	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }

	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setAccessTokenConverter(customAccessTokenConverter);

	        converter.setSigningKey("sample");
	        
	        return converter;
	    }

	    @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore());
	        return defaultTokenServices;
	    }
}
