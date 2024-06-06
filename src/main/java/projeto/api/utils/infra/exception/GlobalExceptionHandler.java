package projeto.api.utils.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> validationExceptionHandler(ValidationException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 400, exc.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exc) {
        List<FieldError> erros = exc.getFieldErrors();

        return ResponseEntity.badRequest().body(
                erros.stream().map(FieldExceptionResponse::new).toList());
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> notFoundExceptionHandler(NotFoundException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 404, exc.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException exc) {
        String message = "Incorrect email or password!";
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 401, message);
        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler({TokenValidationException.class})
    public ResponseEntity<?> tokenValidationExceptionHandler(TokenValidationException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 401, exc.getMessage());
        return ResponseEntity.status(401).body(response);
    }
}

record DefaultExceptionResponse(LocalDateTime timestamp,
                                        Integer status,
                                        String error){}
record FieldExceptionResponse(String field, String message) {
    public FieldExceptionResponse(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}