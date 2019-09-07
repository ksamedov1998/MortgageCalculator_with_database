# MortgageCalculator_with_database

Console Application takes name,surname,borndate and other personal information from user and calcs the monthly payment and total payment.
Then it writes the data into db.<br>

Database: Oracle DB <br>
Driver : oracle.jdbc.driver.OracleDriver
Connection URL : jdbc:oracle:thin:@localhost <or ip>:1521/ORCL

Exception package : 1.IllegalNameException
                    2.IllegalPercentageException
                    3.IllegalSurnameException
                    4.NonAcceptableMoney
                    5.NonAcceptableYear
objbc_driver package :ojdbc8.jar (You should add it into project library)
Default package : 1.Database
                  2.Ipoteka
                  3.MainClass
                  
Database class contains methods for dealing with db processes
Ipoteka (means Mortage) contains method to calc money
MainClass runs application
