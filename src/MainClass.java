import Exceptions.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class MainClass {
    public static void main(String[] args) {
    Ipoteka ipoteka= new Ipoteka();
        try{
            ipoteka.setName();
            ipoteka.setSurname();
            ipoteka.setBorndate();
            ipoteka.setGender();
            ipoteka.setMortgageLong();
            ipoteka.setYear();
            ipoteka.setInitialPayment();
            ipoteka.createCustomer();
            ipoteka.createCredit();
            ipoteka.PaymentTrack();
            Database.closeConnection();
        }catch (NonAcceptableYear e){
            System.out.println(e.getMessage());
            }catch (NonAcceptableMoney e ){
            System.out.println(e.getMessage());
            }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            }catch (IllegalNameException |IllegalSurnameException e){
            System.out.println(e.getMessage());
             }catch (IllegalPercentageException e){
            System.out.println(e.getMessage());
            }
    }
}
