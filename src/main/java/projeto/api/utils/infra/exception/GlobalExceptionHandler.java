package projeto.api.utils.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> validationExceptionHandler(ValidationException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 400, exc.getMessage());
        return ResponseEntity.ok(response);
    }
}

record DefaultExceptionResponse(LocalDateTime timestamp,
                                        Integer status,
                                        String error){}