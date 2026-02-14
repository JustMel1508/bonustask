# Thank You

Thank you for being our teacher. You are truly the best.  
I really appreciate your support, clear explanations, and patience during this course. 
---

![Project Screenshot](docs/555.jpg)

# Project Overview

This project is a Spring Boot REST API for managing Characters and Worlds.  
It demonstrates clean layered architecture, usage of design patterns, JDBC integration, and RESTful API development.

The application follows the standard structure:

Controller → Service → Repository → Database

Each layer has a clear responsibility, which keeps the project organized and easy to maintain.

---

# Core Functionality

- CRUD operations for Characters
- CRUD operations for Worlds
- DTO usage for handling input data
- Global exception handling
- Logging using Singleton pattern
- Factory pattern for flexible character creation
- REST endpoints tested with Postman

---

# Design Patterns Used

## Singleton Pattern

The Singleton pattern is used to ensure that only one instance of certain classes exists during application runtime.

Classes using Singleton:
- AppLogger
- InMemoryCache (Bonus task)

---

## Factory Pattern

CharacterFactory is used to create character objects depending on their type.  
This helps avoid tight coupling and keeps object creation logic separate from business logic.

---

# Bonus Task — Caching Layer (Simple In-Memory Cache)

## Purpose

The goal of the bonus task was to implement a simple in-memory caching mechanism in order to reduce repeated database queries and improve performance.

---

## Implementation Details

The caching system consists of:

- Cache — abstraction interface
- InMemoryCache — Singleton implementation using ConcurrentHashMap
- CacheService — service responsible for cache interaction
- CacheController — endpoint for manual cache clearing

The cache stores data in memory and is thread-safe.

---

## What Is Cached

The result of:

CharacterService#getAll()

Cache key used:

characters.all

If the same GET /characters request is made multiple times, the result is returned from cache instead of querying the database again.

---

## Cache Invalidation

The cache is automatically invalidated when:

- A character is created
- A character is updated
- A character is deleted

This ensures data consistency between cache and database.

---

## Manual Cache Clearing

Endpoint available:

POST /cache/clear

This clears all cached values manually.

