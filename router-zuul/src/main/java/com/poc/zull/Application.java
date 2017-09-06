package com.poc.zull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.poc.zull.filters.pre.FiltroBcpa;

/**
 * 
 * @author julio.cesar.maciel
 *
 */
@PropertySources({
		@PropertySource(value = "file:c:/properties/application.properties", ignoreResourceNotFound = false) })
@Configuration
@ComponentScan("com.poc.zull")
@EnableAutoConfiguration
@EnableZuulProxy
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static Class<Application> applicationClass = Application.class;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	@Bean
	public FiltroBcpa preFilter() {
		return new FiltroBcpa();
	}

}
