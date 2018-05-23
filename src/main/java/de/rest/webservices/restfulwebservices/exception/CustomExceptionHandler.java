package de.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // https://docs.oracle.com/javase/8/docs/api/java/lang/package-tree.html
    @ExceptionHandler({
            CloneNotSupportedException.class, InterruptedException.class, ClassNotFoundException.class,
            IllegalAccessException.class, InstantiationException.class, NoSuchFieldException.class,
            NoSuchMethodException.class, ArithmeticException.class, ArrayStoreException.class,
            ClassCastException.class, EnumConstantNotPresentException.class, IllegalThreadStateException.class,
            NumberFormatException.class, IllegalMonitorStateException.class, IllegalStateException.class,
            ArrayIndexOutOfBoundsException.class, StringIndexOutOfBoundsException.class,
            NegativeArraySizeException.class, NullPointerException.class, SecurityException.class,
            TypeNotPresentException.class, UnsupportedOperationException.class
    })
    public final ResponseEntity<Object> handleInternalServerErrors(Exception ex) {

        String uuid = UUID.randomUUID().toString();

        // @todo errorLog loggen. (Logstash, Kibana.)
        // ErrorLog errorLog = new ErrorLog(uuid, ex);

        ErrorMessage errorMessage = ErrorResponseCreator.buildErrorResponseByInternalError(uuid, ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({ ConstraintViolationException.class, org.hibernate.exception.ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {

        ErrorMessage errorMessage = ErrorResponseCreator.buildErrorResponseByConstraintViolation(ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = ErrorResponseCreator.buildErrorResponseByValidationFail(ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
