package com.rptp.rptpSpringBoot;

import com.rptp.rptpSpringBoot.config.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class RptpSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RptpSpringBootApplication.class, args);
	}

}
