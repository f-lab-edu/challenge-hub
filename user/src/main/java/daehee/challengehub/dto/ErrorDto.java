package daehee.challengehub.dto;

import lombok.Getter;

@Getter
public class ErrorDto {
    // Getters
    private final int status;
    private final String errorCode;
    private final String message;

    public ErrorDto(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

}
