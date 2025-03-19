package org.example.listingservice.services.caches;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;

@Service
public class HybridCacheService {
    private final CacheManager caffeineCacheManager;
    private final CacheManager redisCacheManager;

    public HybridCacheService(
            @Qualifier("cacheManager") CacheManager caffeineCacheManager,
            @Qualifier("redisCacheManager") CacheManager redisCacheManager) {
        this.caffeineCacheManager = caffeineCacheManager;
        this.redisCacheManager = redisCacheManager;
    }

    public <T> T getCache(String cacheName, Object key, Class<T> type, Supplier<T> dbSupplier){
        Cache caffeinCache = caffeineCacheManager.getCache(cacheName);
        Cache redisCache = redisCacheManager.getCache(cacheName);

        T value = caffeinCache != null ? caffeinCache.get(key, type) : null;
        if(value != null){
            return value;
        }

        value = redisCache != null ? redisCache.get(key, type) : null;
        if(value != null){
            Objects.requireNonNull(caffeinCache).put(key, value);
            return value;
        }

        value = dbSupplier.get();
        if (value != null) {
            Objects.requireNonNull(caffeinCache).put(key, value);
            Objects.requireNonNull(redisCache).put(key, value);
        }
        return value;

    }

    public void evictCache(String cacheName, Object key){
        Objects.requireNonNull(caffeineCacheManager.getCache(cacheName)).evict(key);
        Objects.requireNonNull(redisCacheManager.getCache(cacheName)).evict(key);
    }



}
