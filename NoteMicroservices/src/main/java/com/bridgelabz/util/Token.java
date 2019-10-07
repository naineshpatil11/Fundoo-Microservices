package com.bridgelabz.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
@Component
public class Token {
	private static String TOKEN;

	public String createToken(String id) {
		TOKEN = "Nainesh";
		Algorithm algorithm = null;

		try {
			algorithm = Algorithm.HMAC256(TOKEN);
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String token = JWT.create().withClaim("ID", id).sign(algorithm);
		return token;
	}
	

	public static String tokenVerify(String token) {
		TOKEN = "Nainesh";

		String adminid;
		// here verify the given token's algorithm
		Verification verification = null;

		try {
			verification = JWT.require(Algorithm.HMAC256(Token.TOKEN));
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JWTVerifier jwtverifier = verification.build();
		DecodedJWT decodedjwt = jwtverifier.verify(token);
		Claim claim = decodedjwt.getClaim("ID");
		adminid = claim.asString();
		System.out.println(adminid);
		return adminid;
	}
	
	public static String getUrl() {
		 
		 String url="http://localhost:8084/user/";
		 return url; 
		}
	
}
