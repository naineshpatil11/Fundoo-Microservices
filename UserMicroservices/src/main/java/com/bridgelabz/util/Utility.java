package com.bridgelabz.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
@Component
public class Utility {
	 public static void send(String toEmail, String subject, String body) {
	        final String fromEmail = "naineshpatil11@gmail.com"; // requires valid gmail id
	        final String password = "7798353210"; // correct password for gmail id

	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
	        props.put("mail.smtp.port", "587"); // TLS Port
	        props.put("mail.smtp.auth", "true"); // enable authentication
	        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

	        // to check email sender credentials are valid or not
	        Authenticator auth = new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(fromEmail, password);
	            }
	        };

	        javax.mail.Session session = Session.getInstance(props, auth);

	        try {
	            MimeMessage msg = new MimeMessage(session);

	            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	            msg.addHeader("format", "flowed");
	            msg.addHeader("Content-Transfer-Encoding", "8bit");

	            msg.setFrom(new InternetAddress("no_reply@gmail.com", "NoReply"));

	            msg.setReplyTo(InternetAddress.parse("naineshpatil11@gmail.com", false));

	            msg.setSubject(subject, "UTF-8");

	            msg.setText(body, "UTF-8");

	            msg.setSentDate(new Date());

	            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	            Transport.send(msg);

	            System.out.println("Email Sent Successfully.........");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	    /**
	     * @param id
	     * @return
	     * @throws UnsupportedEncodingException 
	     */
	    public static String getUrl(String id) throws UnsupportedEncodingException {
	    	UserToken tokenUtil = new UserToken();
	        return "http://localhost:8084/user/" + tokenUtil.createToken(id);
	    }
	    public static String getResetUrl(String id) {
	    	UserToken tokenUtil = new UserToken();
	    	return "http://localhost:8084/user/reset"+tokenUtil.createToken(id);
	    }
}
