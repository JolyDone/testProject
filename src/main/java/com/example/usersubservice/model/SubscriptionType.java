package com.example.usersubservice.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SubscriptionType {
    NETFLIX(1L, "Netflix"),
    YOUTUBE_PREMIUM(2L, "YouTube Premium"),
    SPOTIFY(3L, "Spotify"),
    APPLE_MUSIC(4L, "Apple Music");

    private final Long id;
    private final String serviceName;

    SubscriptionType(Long id, String serviceName) {
        this.id = id;
        this.serviceName = serviceName;
    }

    public static Long getIdForService(String serviceName) {
        return Arrays.stream(values())
                .filter(s -> s.serviceName.equalsIgnoreCase(serviceName))
                .findFirst()
                .map(s -> s.id)
                .orElseThrow(() -> new IllegalArgumentException("Unknown service: " + serviceName));
    }

    public static boolean isValidService(String serviceName) {
        return Arrays.stream(values())
                .anyMatch(s -> s.serviceName.equalsIgnoreCase(serviceName));
    }
}

