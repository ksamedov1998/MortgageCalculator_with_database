package Exceptions;

public class IllegalPercentageException extends Exception {
    private String message;
    public IllegalPercentageException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
