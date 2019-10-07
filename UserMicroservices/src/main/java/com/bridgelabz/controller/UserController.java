package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.dto.UserDTO;
import com.bridgelabz.response.Response;
import com.bridgelabz.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@RequestBody UserDTO userDTO){
		System.out.println("UserController.registerUser()");
		Response response = userService.register(userDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO){
		System.out.println("UserController.login()");
		Response response = userService.login(loginDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PostMapping("/forgetpassword")
	public ResponseEntity<Response> forgetPassword(@RequestParam String email){
		System.out.println("AdminController.forgetPassword()");
		Response response = userService.forgetPassword(email);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping("/resetpassword")
	public ResponseEntity<Response> resetPassword(@RequestBody String password,@RequestParam String id ){
		System.out.println("AdminController.resetPassword()");
		Response response = userService.resetPassword(password,id );
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
