# MortgageCalculator_with_database

Console Application takes name,surname,borndate and other personal information from user and calcs the monthly payment and total payment.
Then it writes the data into db.<br>

Database: Oracle DB <br>
Driver : oracle.jdbc.driver.OracleDriver <br>
Connection URL : jdbc:oracle:thin:@localhost <or ip>:1521/ORCL <br>
 <br>
Exception package : <br>
              &nbsp;    &nbsp;  1.IllegalNameException <br>
              &nbsp;    &nbsp;  2.IllegalPercentageException <br>
              &nbsp;    &nbsp;  3.IllegalSurnameException <br>
              &nbsp;    &nbsp;  4.NonAcceptableMoney <br>
              &nbsp;    &nbsp;  5.NonAcceptableYear <br>
objbc_driver package : <br>
              &nbsp;    &nbsp; 1.ojdbc8.jar (You should add it into project library) <br>
Default package : <br>
              &nbsp; &nbsp;  1.Database <br>
              &nbsp; &nbsp;   2.Ipoteka <br>
              &nbsp;  &nbsp;   3.MainClass <br>
                   <br>
<li>Database class contains methods for dealing with db processes <br></li>
Ipoteka (means Mortage) contains method to calc money <br>
MainClass runs application <br>
