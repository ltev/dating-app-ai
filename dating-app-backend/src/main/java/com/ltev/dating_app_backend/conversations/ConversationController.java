package com.ltev.dating_app_backend.conversations;

import com.ltev.dating_app_backend.profiles.ProfileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("conversations")
@AllArgsConstructor
@Log4j2
public class ConversationController {

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    private record CreateConversationRequest (
            String[] profilesId
    ) {}

    @PostMapping
    public Conversation createNewConversation(@RequestBody CreateConversationRequest request) {
        if (request.profilesId() == null || request.profilesId.length != 2 || request.profilesId()[0].equals(request.profilesId()[1])) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid profileId");
        }
        profileRepository.findById(request.profilesId[0])
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid profileId"));
        profileRepository.findById(request.profilesId[1])
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid profileId"));

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profilesId(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);

        log.info("New Conversation id = " + conversation.id());
        return conversation;
    }

    @PostMapping("/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId,
                                                 @RequestBody ChatMessage chatMessage) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid conversationId"));
        if (! conversation.isParticipant(chatMessage.authorId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid author");
        }

        ChatMessage newMessage = ChatMessage.withCurrentDateTime(chatMessage);
        conversation.messages().add(newMessage);

        log.info("New message = " + newMessage);
        return conversationRepository.save(conversation);
    }

    @GetMapping("/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid conversationId"));
    }
}
