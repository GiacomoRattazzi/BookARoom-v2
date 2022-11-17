package bookaroom.v2.exceptions;

/**
 * @author Team BookARoom
 */

public class InvalidCreditCardDateException extends Exception {
    private String message;
    
    public InvalidCreditCardDateException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
