package br.com.people.security;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import br.com.people.enitities.User;
import br.com.people.utils.UserUtils;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtSecurity {
	private static final Key PRIVATE_KEY = Keys
			.hmacShaKeyFor("PALINDROMO, esta chave deve ter mais de 256 bits".getBytes());

	public static String userToJwt(User user) {

		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MONTH, 1);

		return Jwts.builder().setClaims(UserUtils.userToMap(user)).setIssuedAt(new Date())
				.setExpiration(expiration.getTime()).signWith(PRIVATE_KEY).compact();
	}

	public static User userFromJwt(String token) throws JwtException {
		return UserUtils.userFromMap(Jwts.parser().setSigningKey(PRIVATE_KEY).parseClaimsJws(token).getBody());
	}

}
