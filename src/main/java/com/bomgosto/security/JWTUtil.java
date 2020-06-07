package com.bomgosto.security;

import java.util.Date;
import java.util.Optional;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean tokenValido(String token){
		Claims claims = getClaims(token);
		if(Optional.ofNullable(claims).isPresent()){
			String userName = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			return Optional.ofNullable(userName).isPresent() &&
					Optional.ofNullable(expirationDate).isPresent() &&
					now.before(expirationDate);
		}
		return false;
	}

	public String getUserName(String token){
		Claims claims = getClaims(token);
		if(Optional.ofNullable(claims).isPresent()){
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e){
			return null;
		}
	}

}
