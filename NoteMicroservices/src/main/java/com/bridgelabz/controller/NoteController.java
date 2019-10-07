package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.NoteDTO;
import com.bridgelabz.response.Response;
import com.bridgelabz.service.INoteService;
@RestController
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	private INoteService noteService;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDTO noteDTO, @RequestHeader String token){
		System.out.println("NoteController.createNote()");
		Response response = noteService.createNote(noteDTO, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<Response> updateNote(@RequestBody NoteDTO noteDTO, @RequestHeader String token, @RequestParam String id){
		System.out.println("NoteController.updateNote()");
		Response response = noteService.updateNote(noteDTO,token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@DeleteMapping("delete")
	public ResponseEntity<Response> delete(@RequestParam String id, @RequestHeader String token){
		System.out.println("NoteController.delete()");
		Response response = noteService.deleteNote(id,token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping("/archive")
	public ResponseEntity<Response> archiveAndUnarchive(@RequestHeader String token,@RequestParam String id){
		Response response = noteService.archiveAndUnarchive(token,id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
