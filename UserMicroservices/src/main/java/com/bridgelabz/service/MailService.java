package com.bridgelabz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgelabz.model.Email;
import com.bridgelabz.util.UserToken;
@Component
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private UserToken userToken;
	
	 public void send(Email email) {
			SimpleMailMessage message = new SimpleMailMessage();
		//	SimpleMailMessage message = new SimpleMailMessage(); 
		    message.setTo(email.getTo()); 
		    message.setSubject(email.getSubject()); 
		    message.setText(email.getBody());
		    
		    System.out.println("Sending Email ");
		    
		    mailSender.send(message);

		    System.out.println("Email Sent Successfully!!");

		}
		public String getLink(String link,String id) 
		{
			return link+userToken.createToken(id);
		}
}
