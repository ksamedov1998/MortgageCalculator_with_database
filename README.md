# MortgageCalculator_with_database

Console Application takes name,surname,borndate and other personal information from user and calcs the monthly payment and total payment.
Then it writes the data into db.<br>

Database: Oracle DB <br>
Driver : oracle.jdbc.driver.OracleDriver <br>
Connection URL : jdbc:oracle:thin:@localhost <or ip>:1521/ORCL <br>
 <br>
Exception package : 1.IllegalNameException <br>
                  &nbsp  2.IllegalPercentageException <br>
                    3.IllegalSurnameException <br>
                    4.NonAcceptableMoney <br>
                    5.NonAcceptableYear <br>
objbc_driver package :ojdbc8.jar (You should add it into project library) <br>
Default package : 1.Database <br>
                  2.Ipoteka <br>
                  3.MainClass <br>
                   <br>
Database class contains methods for dealing with db processes <br>
Ipoteka (means Mortage) contains method to calc money <br>
MainClass runs application <br>
