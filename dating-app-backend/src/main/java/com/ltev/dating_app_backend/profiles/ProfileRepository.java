package com.ltev.dating_app_backend.profiles;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProfileRepository extends MongoRepository<Profile, UUID > {

}
