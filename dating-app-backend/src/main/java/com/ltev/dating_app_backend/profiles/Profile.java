package com.ltev.dating_app_backend.profiles;

public record Profile(
        String id,
        String username,
        int age,
        Gender gender,
        String ethnicity,
        String bio,
        String imageUrl,
        String personalityType
) {
}
