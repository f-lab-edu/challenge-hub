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
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorDto errorDto = new ErrorDto(errorCode.getHttpStatus(), errorCode.getErrorCode(), errorCode.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorCode.getHttpStatus()));
    }
}
