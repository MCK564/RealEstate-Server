package org.example.listingservice.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

@Component
public class CacheLoggingInterceptor implements HandlerInterceptor {
    private final CacheManager cacheManager;
    private final StringRedisTemplate redisTemplate;

    public CacheLoggingInterceptor(CacheManager cacheManager, StringRedisTemplate redisTemplate) {
        this.cacheManager = cacheManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,@NotNull Object handler, Exception ex){
       logCaffeineCache();
       logRedisCache();
    }

    private void logCaffeineCache(){
        System.out.println("====== Caffeine Cache State ========");
        System.out.println("Using CacheManager: " + cacheManager.getClass().getName());
        for( String cacheName: cacheManager.getCacheNames()){
            Cache cache = cacheManager.getCache(cacheName);
            if(cache instanceof CaffeineCache caffeineCache){
                System.out.println("Cache Name: " + cacheName + " | Cache Object: " + caffeineCache.getNativeCache().toString());
            }        if (cache instanceof CaffeineCache caffeineCache) {
                com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = (com.github.benmanes.caffeine.cache.Cache<Object, Object>) caffeineCache.getNativeCache();
                System.out.println("Cache Name: " + cacheName);


                nativeCache.asMap().forEach((key, value) ->
                        System.out.println("  Key: " + key + " | Value: " + value));
            }

        }
    }

    private void logRedisCache(){
        System.out.println("====== Redis Cache State ========");
        Set<String> keys = redisTemplate.keys("*");
        if(keys.isEmpty()){
            System.out.println("No cache keys found");
        }
        for(String key: keys){
            String value  = redisTemplate.opsForValue().get(key);
            System.out.println("Cache Key: " + key + " | Cache Value: " + value);
        }
    }
}
