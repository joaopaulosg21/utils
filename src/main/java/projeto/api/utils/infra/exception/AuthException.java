package projeto.api.utils.infra.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
