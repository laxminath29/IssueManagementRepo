package com.nxtlife.efkon.msil.issueManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource-server-api";
	private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
	private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
	private static final String SECURED_PATTERN = "/incidents/support/**";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class).requestMatchers()
				.antMatchers("/incidents/v1").antMatchers("/incidents/v1/*").antMatchers(SECURED_PATTERN).and()
				.authorizeRequests().antMatchers("/incidents/v1").permitAll().antMatchers("/incidents/v1/*").permitAll()
				.antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE).and().authorizeRequests()
				.antMatchers(HttpMethod.PUT, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
				.antMatchers(HttpMethod.PUT, SECURED_PATTERN).access(SECURED_READ_SCOPE).anyRequest()
				.access(SECURED_READ_SCOPE);
	}
}
