package com.fatihsengun.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	public static final String SECRET_KEY = "8sG8VeJc02GAhr6/CCt+5b9ZBkKrAWwSpH2IAxrhAss=";

	public String generateToken(UserDetails userDetails) {

		return Jwts.builder().subject(userDetails.getUsername()).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)).signWith(getKey()).compact();

	}

	public SecretKey getKey() {
		byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decode);
	}

	public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
		Claims claims = Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
		return claimsFunction.apply(claims);
	}

	public String getUsernameByToken(String token) {
		return exportToken(token, Claims::getSubject);
	}

	public boolean isTokenExpired(String token) {
		Date expireDate = exportToken(token, Claims::getExpiration);
		return new Date().before(expireDate);

	}

}
