package com.ltev.dating_app_backend.conversations;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, Long> {
}
