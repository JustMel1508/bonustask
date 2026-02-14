package org.example.cache;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryCache implements Cache {
    private static volatile InMemoryCache instance;

    private final ConcurrentHashMap<String, Object> store = new ConcurrentHashMap<>();

    private InMemoryCache() {
    }

    public static InMemoryCache getInstance() {
        InMemoryCache local = instance;
        if (local == null) {
            synchronized (InMemoryCache.class) {
                local = instance;
                if (local == null) {
                    local = new InMemoryCache();
                    instance = local;
                }
            }
        }
        return local;
    }

    @Override
    public <T> Optional<T> get(String key, Class<T> type) {
        Object v = store.get(key);
        if (v == null) return Optional.empty();
        if (!type.isInstance(v)) return Optional.empty();
        return Optional.of(type.cast(v));
    }

    @Override
    public void put(String key, Object value) {
        if (value == null) {
            store.remove(key);
            return;
        }
        store.put(key, value);
    }

    @Override
    public void invalidate(String key) {
        store.remove(key);
    }

    @Override
    public void clear() {
        store.clear();
    }
}
