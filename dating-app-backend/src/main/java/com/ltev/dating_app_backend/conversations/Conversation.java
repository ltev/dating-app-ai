package com.ltev.dating_app_backend.conversations;

import java.util.List;

public record Conversation(
        String id,
        String[] profilesId,
        List<ChatMessage> messages) {

    public boolean isParticipant(String profileId) {
        for (var id : profilesId) {
            if (id.equals(profileId)) {
                return true;
            }
        }
        return false;
    }
}
