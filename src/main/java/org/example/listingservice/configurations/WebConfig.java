package org.example.listingservice.configurations;

import org.example.listingservice.interceptors.CacheLoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CacheLoggingInterceptor cacheLoggingInterceptor;

    public WebConfig(CacheLoggingInterceptor cacheLoggingInterceptor) {
        this.cacheLoggingInterceptor = cacheLoggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cacheLoggingInterceptor);
    }
}
