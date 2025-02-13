package com.ltev.dating_app_backend.conversations;

import java.util.List;
import java.util.UUID;

public record Conversation(
        Long id,
        UUID profileId,
        List<ChatMessage> messages
) {
}
