package com.bridgelabz.service;

 
import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dto.NoteDTO;
import com.bridgelabz.model.Note;
import com.bridgelabz.repository.NoteRepository;
import com.bridgelabz.response.Response;
import com.bridgelabz.util.Token;

@Service
public class NoteServiceImpl implements INoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Token token;
	
	// RestTemplate template = new RestTemplate();
	 
	@Override
	public Response createNote(NoteDTO noteDTO, String token) {
		String userId = Token.tokenVerify(token);
		Optional<Note> user = noteRepository.findById(userId);
//		if(!user.isPresent()) {
//			return new Response(400,"User not present",null);
//		}
		System.out.println("Id: "+userId);
		Note note = modelMapper.map(noteDTO, Note.class);
		note.setCurrentTime(LocalDateTime.now());
		note.setUpdateTime(LocalDateTime.now());
		note.setUserId(userId);
		noteRepository.save(note);
		return new Response(200,"Note created..",null);
	}

	@Override
	public Response updateNote(NoteDTO noteDTO, String token, String id) {
		System.out.println("NoteServiceImpl.updateNote()");
		String userId = Token.tokenVerify(token);
		System.out.println("UserId"+userId);
		Optional<Note> note = noteRepository.findByIdAndUserId(id, userId);
		if(!note.isPresent()) {
			return new Response(400,"User not present",null);
		}
		Optional<Note> note1 = noteRepository.findById(id);
		note1.get().setTitle(noteDTO.getTitle());
		note1.get().setDescription(noteDTO.getDescription());
		note1.get().setCurrentTime(LocalDateTime.now());
		note1.get().setUpdateTime(LocalDateTime.now());
		noteRepository.save(note1.get());
		return new Response(200,"Note updated successfully",null);
	}

	@Override
	public Response deleteNote(String id, String token) {
		String userId = Token.tokenVerify(token);
		Optional<Note> note = noteRepository.findByIdAndUserId(id,userId);
		if(!note.isPresent()) {
			return new Response(400,"User not present",null);
		}
		Optional<Note> note1 = noteRepository.findById(id);
		noteRepository.delete(note1.get());
		return new Response(200,"Note deleted..",null);
	}

	@Override
	public Response archiveAndUnarchive(String token, String id) {
		String userId = Token.tokenVerify(token);
		System.out.println("dsscf"+userId);
		Optional<Note> user = noteRepository.findByIdAndUserId(id, userId);
		if(!user.isPresent()) {
			return new Response(200,"User not present",null);
		}
		Optional<Note> note = noteRepository.findById(id);
		if(note.get().isArchive() == false) {
			note.get().setArchive(true);
			noteRepository.save(note.get());
			return new Response(200,"Note archived..",null);
		}
		note.get().setArchive(false);
		noteRepository.save(note.get());
		return new Response(200,"Note unarchived",null);
	}	 
}
