package lets.play.demo.Exceptions;

public class InvalidePasswordException extends RuntimeException {
    public InvalidePasswordException(String msg) {
        super(msg);
    }
}
