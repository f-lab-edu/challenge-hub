//package daehee.challengehub.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
//        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
//        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
//    }
//
//    private ResponseEntity<Object> buildErrorResponse(Exception ex, HttpStatus status) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, status);
//    }
//}
//
