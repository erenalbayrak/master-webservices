package de.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	// @TODO: Logging.

	/*
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {

		String uuid = UUID.randomUUID().toString();

		// @TODO errorLog loggen
		ErrorLog errorLog = new ErrorLog(uuid, ex);

		ErrorResponse errorResponse = new ErrorResponse(
				"Interner Server-Fehler",
				"Es ist ein Fehler aufgetretten. " +
						"Für eine zukünftige Fehlerbehebung wurde dieses unerartete Verhalten des Servers unter folgendem Code gespeichert: " +
						uuid);

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ErrorResponse errorResponse = new ErrorResponse(
				"Die übermittelten Daten sind fehlerhaft",
				String.join("\n", errors));

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}