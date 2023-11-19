package daehee.challengehub.exception;

import daehee.challengehub.constants.ErrorCode;

public class PasswordException extends CustomException {
    public PasswordException(ErrorCode errorCode) {
        super(errorCode);
    }
}
