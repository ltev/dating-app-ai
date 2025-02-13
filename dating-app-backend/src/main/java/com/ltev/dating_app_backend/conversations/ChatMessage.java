package com.ltev.dating_app_backend.conversations;

import java.time.LocalDateTime;

public record ChatMessage(
        LocalDateTime time,
        String text
) {
}
