package com.bridgelabz.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.activemq.command.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.dto.UserDTO;
import com.bridgelabz.model.Email;
import com.bridgelabz.model.User;
import com.bridgelabz.repository.UserRepository;
import com.bridgelabz.util.UserToken;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserToken userToken;

	@Override
	public com.bridgelabz.response.Response register(UserDTO userDTO) {
		System.out.println("UserServiceImpl.register()");
		Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
		if(user.isPresent()) {
			return new com.bridgelabz.response.Response(400,"User already registered",null);
		}
		User user1 = modelMapper.map(userDTO, User.class);
		user1.setRegisterDate(LocalDateTime.now());
		user1.setUpdateDate(LocalDateTime.now());
		userRepository.save(user1);
		return new com.bridgelabz.response.Response(200,"User registere successfully", null);
	}

	@Override
	public com.bridgelabz.response.Response login(LoginDTO loginDTO) {
		System.out.println("UserServiceImpl.login()");
		Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
		if(!user.isPresent()) {
			return new com.bridgelabz.response.Response(400,"User is not present..",null);
		}
		String token = null;
		token = userToken.createToken(user.get().getUserId());
		if(user.get().getPassword().matches(loginDTO.getPassword()))
		  return new com.bridgelabz.response.Response(200,"Login successfully done..",token);
		return  new com.bridgelabz.response.Response(200,"Password not correct",null);
	}

	@Override
	public com.bridgelabz.response.Response forgetPassword(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if(!user.isPresent()) {
			return new com.bridgelabz.response.Response(400,"User is not present",null);
		}
		Email email1 = new Email();
		email1.setFrom("naineshpatil11@gmail.com");
		email1.setTo(user.get().getEmail());
		email1.setSubject("Forgot password verification");
		email1.setBody(mailService.getLink("http://localhost:8084/user/resetPassword/",user.get().getUserId()));
		mailService.send(email1);
		return new com.bridgelabz.response.Response(200,"Forgate link send on mail",null);
	}

	@Override
	public com.bridgelabz.response.Response resetPassword(String password, String id) {
		Optional<User> user = userRepository.findById(id);
		user.get().setPassword(password);
		userRepository.save(user.get());
		return new com.bridgelabz.response.Response(200,"Password reset successfully",null);
		
	}
}
