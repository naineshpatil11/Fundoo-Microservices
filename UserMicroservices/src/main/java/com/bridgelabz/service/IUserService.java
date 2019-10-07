package com.bridgelabz.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.dto.UserDTO;
import com.bridgelabz.response.Response;

@Service
public interface IUserService {

	public Response register(UserDTO userDTO);

	public Response login(LoginDTO loginDTO);

	public Response forgetPassword(String email);

	public Response resetPassword(String password, String id); 
}
