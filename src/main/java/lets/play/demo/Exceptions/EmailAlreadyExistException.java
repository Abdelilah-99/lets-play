package lets.play.demo.Exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String msg) {
        super(msg);
    }
}
