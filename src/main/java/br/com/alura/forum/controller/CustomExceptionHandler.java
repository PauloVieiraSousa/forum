package br.com.alura.forum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alura.forum.controller.dto.output.ErrorOutputDto;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


@ControllerAdvice(basePackages = "br.com.alura.forum.controller")
@Slf4j
public class CustomExceptionHandler {

	@ExceptionHandler({Exception.class, NullPointerException.class})
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public void erro500(Exception exception) {
		exception.printStackTrace();
	}
	
	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity badCredentials(AuthenticationException exception) {
		log.error(exception.getMessage());
		return ResponseEntity.badRequest().body(new ErrorOutputDto("credentials", "dados invalidos"));
	}
	
	
	
}
