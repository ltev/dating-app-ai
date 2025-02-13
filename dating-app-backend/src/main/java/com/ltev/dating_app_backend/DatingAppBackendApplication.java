package com.ltev.dating_app_backend;

import com.ltev.dating_app_backend.conversations.ChatMessage;
import com.ltev.dating_app_backend.conversations.Conversation;
import com.ltev.dating_app_backend.conversations.ConversationRepository;
import com.ltev.dating_app_backend.profiles.Gender;
import com.ltev.dating_app_backend.profiles.Profile;
import com.ltev.dating_app_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class DatingAppBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(DatingAppBackendApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Profile profile = new Profile(
				UUID.randomUUID(),
				"eva88",
				40,
				Gender.FEMALE,
				"Indian",
				"hard worker all the time",
				"eva88.jpg",
				"not sure"
		);
		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);

		Conversation conversation = new Conversation(
				1L,
				profile.id(),
				List.of(
						new ChatMessage(
								LocalDateTime.now(),
								"How are you doing?"
						),
						new ChatMessage(
								LocalDateTime.now(),
								"Hope to hear from you soon."
						)
				)
		);
		conversationRepository.save(conversation);
		System.out.println(conversationRepository.findAll());
	}
}
