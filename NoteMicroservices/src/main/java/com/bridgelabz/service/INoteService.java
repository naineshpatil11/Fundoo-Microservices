package com.bridgelabz.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.dto.NoteDTO;
import com.bridgelabz.response.Response;

@Service
public interface INoteService {

	 Response createNote(NoteDTO noteDTO, String token);

	 Response updateNote(NoteDTO noteDTO, String token, String id);

	Response deleteNote(String id, String token);

	Response archiveAndUnarchive(String token, String id);

}

