package lets.play.demo.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> HandleRuntimeException(RuntimeException ex, WebRequest req) {
        Map<String, Object> errRes = new HashMap<>();
        errRes.put("timestamp", LocalDateTime.now());
        errRes.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        errRes.put("message", ex.getMessage());
        errRes.put("path", req.getDescription(false));
        return new ResponseEntity<>(errRes, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
