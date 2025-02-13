package com.ltev.dating_app_backend;

import com.ltev.dating_app_backend.profiles.Gender;
import com.ltev.dating_app_backend.profiles.Profile;
import com.ltev.dating_app_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class DatingAppBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

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
	}
}
