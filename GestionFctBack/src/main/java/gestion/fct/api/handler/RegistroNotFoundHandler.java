package gestion.fct.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gestion.fct.exception.RegistroNotFoundException;
@ControllerAdvice
public class RegistroNotFoundHandler {

	@ExceptionHandler (RegistroNotFoundException.class)
	public ResponseEntity<String> handle(RegistroNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
