package com.bridgelabz.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, String>{

	public Optional<User> findByEmail(String email);
}
