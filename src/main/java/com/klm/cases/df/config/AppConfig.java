package com.klm.cases.df.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
@ConfigurationProperties("application.properties")
@Getter
@Setter
public class AppConfig {
	
	@Value("${travel.serviceApiUrl}")
	private String serviceApiUrl;
	
	@Value("${travel.actuatorUrl}")
	private String actuatorUrl;

	@Value("${userName}")
	private String userName;

	@Value("${password}")
	private String password;

	@Bean
	RestOperations getRestOperations(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.basicAuthentication(userName,password).build();
	}

	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
