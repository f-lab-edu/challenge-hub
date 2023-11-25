package daehee.challengehub.constants;

import lombok.Getter;

// TODO: Error 관련 코드들 싹 다 합쳐야겠다.
@Getter
public enum ErrorCode {
    INVALID_PARAMETER(400, "INVALID_PARAMETER", "파라미터 값을 확인해주세요.");
    private final int httpStatus;
    private final String errorCode;
    private final String message;

    ErrorCode(int httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

}
