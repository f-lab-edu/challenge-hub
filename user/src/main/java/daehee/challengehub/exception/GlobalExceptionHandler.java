package daehee.challengehub.exception;

import daehee.challengehub.constants.ErrorCode;
import daehee.challengehub.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> handleAuthenticationException(AuthenticationException ex) {
        return createErrorResponse(ex);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<ErrorDto> handlePasswordException(PasswordException ex) {
        return createErrorResponse(ex);
    }

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<ErrorDto> createErrorResponse(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorDto errorDto = new ErrorDto(errorCode.getStatus(), errorCode.getMessage());
        log.error("Exception occurred: {}", errorCode.getMessage(), ex);
        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorCode.getStatus()));
    }
}
