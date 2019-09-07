package Exceptions;

public class IllegalSurnameException extends Exception{
    String surname;
    public IllegalSurnameException(String message) {
        surname=message;
    }

    @Override
    public String getMessage() {
        return surname+" contains different characters";
    }
}
