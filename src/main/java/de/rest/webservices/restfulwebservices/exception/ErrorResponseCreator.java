package de.rest.webservices.restfulwebservices.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponseCreator {

    // @todo wenn Logstash & Kibana umgesetzt sind, soll der Parameter "ex" raus & die Antwort (message) nur bis uuid gehen. Zur Zeit nur zwecks delevlopment/debug drinne.
    public static ErrorMessage buildErrorResponseByInternalError(String uuid, Exception ex) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        return new ErrorMessage(
                "Interner Server-Fehler",
                "Es ist ein Fehler aufgetreten. " +
                        "Für eine zukünftige Fehlerbehebung wurde dieses unerwartete Verhalten des Servers unter folgendem Code gespeichert: " +
                        uuid + "\n" +
                        ex + "\n" +
                        "Stacktrace:\n" + pw.toString());
    }

    // Nützlich wenn @Valid im Controller verwendet werden kann wie in save::UISettingsController.
    // Denn dann greift im Fehlerfall die Methode handleMethodArgumentNotValid::CustomExceptionHandler
    // In handleMethodArgumentNotValid::CustomExceptionHandler kann z.B. diese Methode aufgerufen werden.
    public static ErrorMessage buildErrorResponseByValidationFail(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage()); // Bsp: "birthDate: muss in der Vergangenheit liegen"
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        return new ErrorMessage(
                "Die übermittelten Daten sind fehlerhaft",
                String.join("\n", errors));
    }

    // Nützlich wenn @Valid NICHT im Controller verwendet werden kann.
    // Stattdessen im Controller eigenen dynamischen Validator benutzen und diese Methode aufrufen.
    public static ErrorMessage buildErrorResponseByValidationFail(Errors errors) {

        List<String> errorList = new ArrayList<>();
        for (ObjectError actError : errors.getAllErrors()){
            errorList.add( actError.getObjectName() + ": " + actError.getDefaultMessage());
        }

        return new ErrorMessage(
                "Die übermittelten Daten sind fehlerhaft",
                String.join("\n", errorList));
    }

    public static ErrorMessage buildErrorResponseByConstraintViolation(ConstraintViolationException ex) {

        List<String> errors = new ArrayList<>();

        ex.getConstraintViolations().iterator().forEachRemaining(actViolation -> {
            errors.add(actViolation.getPropertyPath().toString() + ": " + actViolation.getMessage());
        });

        return new ErrorMessage(
                "Die übermittelten Daten sind fehlerhaft",
                String.join("\n", errors));
    }
}
