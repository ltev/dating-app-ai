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

import java.time.LocalDateTime;
import java.util.List;

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
		profileRepository.deleteAll();
		conversationRepository.deleteAll();

		Profile profile1 = new Profile(
				"1",
				"eva88",
				40,
				Gender.FEMALE,
				"Indian",
				"hard worker all the time",
				"eva88.jpg",
				"not sure"
		);
		profileRepository.save(profile1);
		Profile profile2 = new Profile(
				"2",
				"dave90",
				40,
				Gender.MALE,
				"Indian",
				"hard worker all the time",
				"dave.jpg",
				"eager"
		);
		profileRepository.save(profile2);

		profileRepository.findAll().forEach(System.out::println);

		Conversation conversation = new Conversation(
				"1",
				new String[] {profile1.id(), profile2.id()},
				List.of(
						new ChatMessage(
								"1",
								LocalDateTime.now(),
								"How are you doing?"
						),
						new ChatMessage(
								"1",
								LocalDateTime.now(),
								"Hope to hear from you soon. bye"
						)
				)
		);
		conversationRepository.save(conversation);
		conversationRepository.findAll().forEach(System.out::println);
	}
}
