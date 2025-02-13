package com.ltev.dating_app_backend.profiles;

import java.util.UUID;

public record Profile(
        UUID id,
        String username,
        int age,
        Gender gender,
        String ethnicity,
        String bio,
        String imageUrl,
        String personalityType
) {
}
