package br.com.people.utils;

import java.util.HashMap;
import java.util.Map;

import br.com.people.enitities.User;

public class UserUtils {

	private static final String ID = "id";
	private static final String FIRSTNAME = "firstname";
	private static final String SURNAME = "surname";
	private static final String EMAIL = "email";

	public static Map<String, Object> userToMap(User user) {
		Map<String, Object> claims = new HashMap<String, Object>();

		claims.put(ID, user.getId());
		claims.put(FIRSTNAME, user.getFirstname());
		claims.put(SURNAME, user.getSurname());
		claims.put(EMAIL, user.getEmail());

		return claims;
	}

	public static User userFromMap(Map<String, Object> userMap) {
		User user = new User();

		user.setId(new Long((int) userMap.get(ID)));
		user.setEmail((String) userMap.get(EMAIL));
		user.setFirstname((String) userMap.get(FIRSTNAME));
		user.setSurname((String) userMap.get(SURNAME));

		return user;
	}
}
