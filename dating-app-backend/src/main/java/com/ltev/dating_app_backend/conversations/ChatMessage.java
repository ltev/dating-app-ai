package com.ltev.dating_app_backend.conversations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public record ChatMessage(
        String authorId,
        LocalDateTime dateTime,
        String text
) {

    public static ChatMessage withCurrentDateTime(ChatMessage chatMessage) {
        return new ChatMessage(
                chatMessage.authorId,
                ZonedDateTime.now().toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(),
                chatMessage.text);
    }
}
