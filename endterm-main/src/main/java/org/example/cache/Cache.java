package org.example.cache;

import java.util.Optional;

public interface Cache {
    <T> Optional<T> get(String key, Class<T> type);

    void put(String key, Object value);

    void invalidate(String key);

    void clear();
}
