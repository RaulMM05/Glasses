package gestion.fct.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gestion.fct.exception.UserServiceException;
@ControllerAdvice
public class UserServiceHandler {

	@ExceptionHandler (UserServiceException.class)
	public ResponseEntity<String> handle(UserServiceException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
