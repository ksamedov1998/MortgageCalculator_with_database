import Exceptions.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
/*
*           ipoteka.setName();
            ipoteka.setSurname();
            ipoteka.setBorndate();
            ipoteka.setDate();
            ipoteka.setInitialPayment();
            ipoteka.createCostumer();
            ipoteka.createCredit();
            ipoteka.PaymentTrack();
 */
public class Ipoteka {
    private String name;
    private String surname;
    private String bornDate;
    private BigDecimal mortgageMount;
    private final  int MAXAGEFORMALE=67;
    private final int MAXAGEFORFEMALE=65;
    private final BigDecimal MAXMONEY=BigDecimal.valueOf(150000);
    private  int age;
    private int canTake;
    private char gender;
    private BigDecimal houseCost;
    private BigDecimal initialPayment;
    private int year;
    private int percentage;
    private LocalDate bornLocalDate;
    private BigDecimal percentForYear=BigDecimal.valueOf(0.08); //can be change
    private BigDecimal percentForMonth=(percentForYear.divide(BigDecimal.valueOf(12),9,RoundingMode.HALF_DOWN));
    private Scanner scn= new Scanner(System.in);
    private BigDecimal monthlyPayment;
    private LocalDate time= LocalDate.now();
    private BigDecimal monthlyInterestAmount;
    private BigDecimal monthlyBaseAmount;
    private BigDecimal interestAmount;


    public void setName() throws IllegalNameException{  //sets customer name , otherwise throw IllegalNameException
        System.out.println("Enter your name");
        name=scn.nextLine();
        if(!(name.matches("[a-zA-Z]{3,}"))){    //Name should contain more than 3 symbol
            throw new IllegalNameException(name);
        }
    }
    public void setSurname() throws IllegalSurnameException { //sets customer surname , otherwise throw IllegalSurnameException
        System.out.println("Enter your surname");
        surname=scn.nextLine();
        if(!(surname.matches("[a-zA-Z]{3,}"))){     //Surname should contain more than 3 symbol
            throw new IllegalSurnameException(surname);
        }
    }

    public void setBorndate() throws DateTimeParseException, NonAcceptableYear {        //takes borndate as string and parses it to LocalDate (patter: d-M-yyyy (28-04-1998))
        System.out.println("Enter your birthday (dd-mm-yyyy) ");                            //otherwise throws DateTimeParseException or NonAcceptableYear Exception
        bornDate=scn.nextLine();
        bornLocalDate=LocalDate.parse(bornDate, DateTimeFormatter.ofPattern("d-M-yyyy"));
        setAge();
    }


    public void setAge() throws NonAcceptableYear{                          //setBornDate method check age restriction with this method
        age=LocalDate.now().getYear()-bornLocalDate.getYear();
        if(age<=0){
            throw new NonAcceptableYear("Age cannot be less than zero");
        }
    }

    private void checkMoney() throws NonAcceptableMoney {           //check money customer wants , otherwise throws NonAcceptableMoney exception
        if(houseCost.subtract(MAXMONEY).compareTo(BigDecimal.ZERO)<=0){
            if(houseCost.compareTo(BigDecimal.ZERO)<0){
                throw new NonAcceptableMoney("Money you have requested is less than zero" );
            }else if(houseCost.compareTo(BigDecimal.ZERO)==0){
                throw new NonAcceptableMoney("Money you have requested is zero" );
            }
        }else{
            throw new NonAcceptableMoney(houseCost,MAXMONEY);
        }
    }

    public void setGender() {                                               //set gender,because female and male metters for years
        int maxYeartoTake = 0;
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("Female (f) or male (m)?");
            String genderL = scn.nextLine();
            if (genderL.equalsIgnoreCase("f")) {
                gender = 'f';
                break;
            } else if (genderL.equalsIgnoreCase("m")) {
                gender = 'm';
            } else {
                System.out.println("Please enter gender format like for female 'f' , male 'm'");
            }
        }
    }
        public void setMortgageLong() throws NonAcceptableYear{             //set mortgage duration,otherwise throws nonAcceptableYear exception
                canTake=0;
                if(gender=='f'){
                    canTake=(MAXAGEFORFEMALE-age);
                if(canTake<=0){
                    throw new NonAcceptableYear("You cannot get mortgage, because of the age");
                }else{
                    System.out.print("You can take money for "+canTake+", ");
                }
            }else if (gender=='m'){
                    canTake=(MAXAGEFORMALE-age);
                if(canTake<=0){
                    throw new NonAcceptableYear("You cannot get mortgage, because of the age");
                }else{
                    System.out.print("You can take money for "+canTake+", ");
                }
            }else{
                System.out.println("Please enter gender format like for female 'f' , male 'm'");
            }
        }


        public void setYear() throws NonAcceptableYear {     //set mortgage duration,otherwise throws nonAcceptableYear exception
        System.out.println("Please enter year less than "+canTake);
            year=scn.nextInt();
        if (year<=canTake && year>0) {
        }
        else if(year<=0){
            throw new NonAcceptableYear("Date you have entered is less equal than zero");
        }else{
            throw new NonAcceptableYear(year);
        }
    }

    public void setInitialPayment() throws IllegalPercentageException, NonAcceptableMoney {   //there is a formula to calc monthly payment
        System.out.println("Enter home cost u would like to buy");
        houseCost=scn.nextBigDecimal();
        mortgageMount=houseCost;
        setPercentage();
        initialPayment=(houseCost.multiply(BigDecimal.valueOf(percentage)).divide(BigDecimal.valueOf(100)).round(new MathContext(3)));
        houseCost=houseCost.subtract(initialPayment);
        checkMoney();
        System.out.println("Your request has been accepted");
        monthlyPayment=houseCost.multiply(percentForMonth.multiply ((percentForMonth.add(BigDecimal.valueOf(1)).pow(year*12))));
        monthlyPayment=monthlyPayment.divide((((percentForMonth.add(BigDecimal.valueOf(1)))
                                                .pow(year*12))
                                                .subtract(BigDecimal.valueOf(1))),2,RoundingMode.HALF_DOWN);
    }

    public void PaymentTrack(){             //inserts rows into monthly_payment table in database
        for(int i=1;i<=year;i++){
            monthlyBaseAmount=houseCost.multiply(percentForMonth).round(new MathContext(2));       // calc every year new base amount of month
            monthlyInterestAmount=monthlyPayment.subtract(monthlyBaseAmount);                  // subtract base amount from monthlyPayment and sets as interest amount
            for(int j=1;j<=12;j++){
                time=time.plus(1, ChronoUnit.MONTHS);
                houseCost=houseCost.subtract(monthlyBaseAmount);
                Database.createMonthlyPayment(monthlyBaseAmount,time,monthlyPayment,monthlyInterestAmount);
            }
        }
        System.out.println("Everthing is done, connection closed");
    }

    public void createCustomer()  {                        //inserts row into customer table in database
        Database.createCustomer(name,surname,bornLocalDate);
    }

    public void createCredit() {        //inserts row into credit table in database
        LocalDate lastPayment=time.plusYears(year);
        interestAmount=monthlyPayment.multiply(BigDecimal.valueOf(year*12)).subtract(houseCost);
            Database.createCredit(mortgageMount,initialPayment,houseCost,interestAmount,time.plusMonths(1),lastPayment);
    }

    private void setPercentage() throws IllegalPercentageException {        //sets initialPayment
        System.out.println("Enter initial payment percentage");
        percentage=scn.nextInt();
        if(percentage<30 || percentage>=100){                   //percentage must be more than 30%
            throw new IllegalPercentageException("Initial percentage should be more than 30%");
        }
    }
}
