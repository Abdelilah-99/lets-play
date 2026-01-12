package lets.play.demo.Exceptions;

public class InvalidProductException extends RuntimeException {
    public InvalidProductException(String msg) {
        super(msg);
    }
}
