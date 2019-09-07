package Exceptions;

public class NonAcceptableYear extends Exception {
    private int maxYear;
    private String message;
     public NonAcceptableYear(int year) {
         maxYear=year;
    }
     public NonAcceptableYear(String message){
         this.message=message;
    }


    @Override
    public String getMessage() {
        if(maxYear==0){
            return message;
        }
        return "You can take money for maxsimum "+maxYear;
    }
}
