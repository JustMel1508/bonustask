package org.example.controller;

import org.example.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearAll() {
        cacheService.clearAll();
        return ResponseEntity.noContent().build();
    }
}
