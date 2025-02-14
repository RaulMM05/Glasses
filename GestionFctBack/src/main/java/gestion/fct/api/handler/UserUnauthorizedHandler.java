package gestion.fct.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gestion.fct.exception.UserUnauthorizedException;
@ControllerAdvice
public class UserUnauthorizedHandler {

	@ExceptionHandler (UserUnauthorizedException.class)
	public ResponseEntity<String> handle(UserUnauthorizedException ex){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}
}
