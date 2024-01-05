package daehee.challengehub.common.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "프로필 조회 실패: 해당 사용자 ID로 사용자를 찾을 수 없습니다."),
    INVALID_PARAMETER(400, "INVALID_PARAMETER", "파라미터 값을 확인해주세요."),
    TOKEN_EXPIRED(401, "TOKEN_EXPIRED", "토큰이 만료되었습니다."),
    TOKEN_INVALID(401, "TOKEN_INVALID", "유효하지 않은 토큰입니다."),
    TOKEN_UNKNOWN_ERROR(400, "TOKEN_UNKNOWN_ERROR", "알 수 없는 토큰 오류입니다."),
    INVALID_CREDENTIALS(401, "INVALID_CREDENTIALS", "로그인 실패: 잘못된 이메일 또는 비밀번호"),
    PASSWORD_RESET_FAILED(400, "PASSWORD_RESET_FAILED", "비밀번호 재설정 실패: 현재 비밀번호 불일치"),
    CHALLENGE_ID_REQUIRED(400, "CHALLENGE_ID_REQUIRED", "Challenge ID는 필수입니다.");;


    private final int httpStatus;
    private final String errorCode;
    private final String message;

    ErrorCode(int httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

}
