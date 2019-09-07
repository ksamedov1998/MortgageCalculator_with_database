package Exceptions;

public class IllegalNameException extends Exception {
    String name;
    public IllegalNameException(String message) {
        name=message;
    }

    @Override
    public String getMessage() {
        return name+" contains different characters";
    }
}
