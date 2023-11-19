package daehee.challengehub.exception;

import daehee.challengehub.constants.ErrorCode;

public class AuthenticationException extends CustomException {
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
