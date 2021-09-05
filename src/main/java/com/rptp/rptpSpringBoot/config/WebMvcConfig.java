package com.rptp.rptpSpringBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //CORS 적용할 때 URL 패턴
                .addMapping("/**")
                //자원을 공유할 origin
                .allowedOrigins("*")
                //요청 허용 method
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                //요청 허용 header
                .allowedHeaders("*")
                //cookie 허용
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }
}
