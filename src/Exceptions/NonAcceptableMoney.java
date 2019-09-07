package Exceptions;

import java.math.BigDecimal;

public class NonAcceptableMoney extends Exception {
    private BigDecimal amount=BigDecimal.ZERO;
    private BigDecimal maxAmount=BigDecimal.ZERO;
    String message;
    public NonAcceptableMoney(BigDecimal money,BigDecimal maxsimumCanBeTaken) {
        maxAmount=maxsimumCanBeTaken;
        amount=money;
    }
    public NonAcceptableMoney(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        if(maxAmount.equals(BigDecimal.ZERO) && amount.equals(BigDecimal.ZERO)){
            return message;
        }
        return "You can take maxsimum "+maxAmount+", but "+amount+" has been requested";
    }
}
