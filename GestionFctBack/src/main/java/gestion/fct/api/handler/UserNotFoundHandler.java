sasasasawr3fr						dfefefefefe				
iuuiiuiu			





























package gestion.fct.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gestion.fct.exception.UserNotFound;
@ControllerAdvice
public class UserNotFoundHandler {

	@ExceptionHandler (UserNotFound.class)
	public ResponseEntity<String> handle(UserNotFound ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
