package br.com.people.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.people.enitities.User;
import br.com.people.exceptions.AuthenticationException;
import br.com.people.security.JwtSecurity;
import enumerations.HttpHeaderEnum;
import enumerations.RequestAttributeEnum;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token = (String) request.getHeader(HttpHeaderEnum.Bearer.toString());

		try {

			if (token == null)
				throw new AuthenticationException(
						"This service requires 'Bearer' header attribute, with Authentication token.");

			User userFromToken = JwtSecurity.userFromJwt(token);

			if (userFromToken == null || userFromToken.getId() == null)
				throw new AuthenticationException("Could not resolve authenticated user!");

			request.setAttribute(RequestAttributeEnum.USER.toString(), userFromToken);
			return true;

		} catch (Exception e) {

			response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {}

}