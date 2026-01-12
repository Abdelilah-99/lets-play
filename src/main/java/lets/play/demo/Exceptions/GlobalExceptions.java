package lets.play.demo.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.mongodb.DuplicateKeyException;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Error: This email is already registered.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> HandleRuntimeException(RuntimeException ex, WebRequest req) {
        Map<String, Object> errRes = new HashMap<>();
        errRes.put("timestamp", LocalDateTime.now());
        errRes.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        errRes.put("message", ex.getMessage());
        errRes.put("path", req.getDescription(false));
        return new ResponseEntity<>(errRes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> HandleEmailAlreadyExistException(EmailAlreadyExistException ex,
            WebRequest req) {
        Map<String, Object> errRes = new HashMap<>();
        errRes.put("timestamp", LocalDateTime.now());
        errRes.put("status", HttpStatus.BAD_REQUEST);
        errRes.put("message", ex.getMessage());
        errRes.put("path", req.getDescription(false));
        return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Map<String, Object>> HandleEmailNotFoundException(EmailNotFoundException ex,
            WebRequest req) {
        Map<String, Object> errRes = new HashMap<>();
        errRes.put("timestamp", LocalDateTime.now());
        errRes.put("status", HttpStatus.NOT_FOUND);
        errRes.put("message", ex.getMessage());
        errRes.put("path", req.getDescription(false));
        return new ResponseEntity<>(errRes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidePasswordException.class)
    public ResponseEntity<Map<String, Object>> HandleInvalidePasswordException(InvalidePasswordException ex,
            WebRequest req) {
        Map<String, Object> errRes = new HashMap<>();
        errRes.put("timestamp", LocalDateTime.now());
        errRes.put("status", HttpStatus.BAD_REQUEST);
        errRes.put("message", ex.getMessage());
        errRes.put("path", req.getDescription(false));
        return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<Map<String, Object>> HandleInvalidProductException(InvalidProductException ex,
            WebRequest req) {
        Map<String, Object> errRes = new HashMap<>();
        errRes.put("timestamp", LocalDateTime.now());
        errRes.put("status", HttpStatus.NOT_FOUND);
        errRes.put("message", ex.getMessage());
        errRes.put("path", req.getDescription(false));
        return new ResponseEntity<>(errRes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Map<String, Object>> HandleAuthorizationDeniedException(AuthorizationDeniedException ex,
            WebRequest req) {
        Map<String, Object> errRes = new HashMap<>();
        errRes.put("timestamp", LocalDateTime.now());
        errRes.put("status", HttpStatus.FORBIDDEN);
        errRes.put("message", ex.getMessage());
        errRes.put("path", req.getDescription(false));
        return new ResponseEntity<>(errRes, HttpStatus.FORBIDDEN);
    }
}
