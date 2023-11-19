package daehee.challengehub.constants;

public enum ErrorCode {
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),
    TOKEN_EXPIRED(401, "토큰이 만료되었습니다."),
    TOKEN_INVALID(401, "유효하지 않은 토큰입니다."),
    TOKEN_UNKNOWN_ERROR(400, "알 수 없는 토큰 오류입니다."),
    INVALID_CREDENTIALS(401, "로그인 실패: 잘못된 이메일 또는 비밀번호"),
    PASSWORD_RESET_FAILED(400, "비밀번호 재설정 실패: 현재 비밀번호 불일치");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
