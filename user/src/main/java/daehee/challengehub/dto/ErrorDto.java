package daehee.challengehub.dto;

public class ErrorDto {
    private final int status;
    private final String message;

    public ErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
