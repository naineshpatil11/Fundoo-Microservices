package com.bridgelabz.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.Note;
@Repository
public interface NoteRepository extends MongoRepository<Note, String>{

	  Optional<Note> findByIdAndUserId(String id,String userId); 
}
