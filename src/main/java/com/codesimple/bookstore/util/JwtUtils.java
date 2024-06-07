package com.codesimple.bookstore.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.codesimple.bookstore.common.AccessDeniedException;
import com.codesimple.bookstore.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static String secret = "This_is_secret";
	private static long expiryDuration = 60 * 60;

	public String generateJwt(User user) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;

		Date issuedAt = new Date(milliTime);
		Date expirydAt = new Date(expiryTime);

		// claims
		Claims claims = Jwts.claims()
				.setIssuer(user.getId().toString())
				.setIssuedAt(issuedAt)
				.setExpiration(expirydAt);

		// optional claims sensitive data don't add

		claims.put("type", user.getUserType());
		claims.put("name", user.getName());
		claims.put("emailId", user.getEmailId());

		// generate jwt using claim

		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();

	}
	
	public void verify(String authorization) throws Exception {
	
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);
			
		}catch(Exception e)
		{
			throw new AccessDeniedException("Access Denied");
		}
	}
}







