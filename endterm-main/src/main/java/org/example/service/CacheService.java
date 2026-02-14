package org.example.service;

import org.example.cache.Cache;
import org.example.cache.InMemoryCache;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    public static final String CHARACTERS_ALL_KEY = "characters.all";

    private final Cache cache;

    public CacheService() {
        this.cache = InMemoryCache.getInstance();
    }

    public Cache getCache() {
        return cache;
    }

    public void invalidateCharactersAll() {
        cache.invalidate(CHARACTERS_ALL_KEY);
    }

    public void clearAll() {
        cache.clear();
    }
}
