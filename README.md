# MortgageCalculator_with_database
 <div><img src="https://www.oracle.com/webfolder/technetwork/tutorials/obe/db/12c/r1/odb_quickstart/images/oracle%20database.gif">
 <img  height="162px" width="207px" style="display:inline;" src="https://www.ophtek.com/wp-content/uploads/2014/08/java_tech.jpg">
<div> <br>
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
<li>Ipoteka (means Mortage) contains method to calc money <br></li>
<li>MainClass runs application <br></li>
 
SQL table : <br>
CREATE TABLE CREDIT <br>
(<br>
  &nbsp;  &nbsp; ID NUMBER NOT NULL <br>
 &nbsp;  &nbsp; , CUSTOMER_ID NUMBER NOT NULL <br>
 &nbsp;  &nbsp; , HOME_PRICE NUMBER NOT NULL <br>
 &nbsp;  &nbsp; , INITIAL_PAYMENT NUMBER NOT NULL <br>
 &nbsp;  &nbsp; , CREDIT_AMOUNT NUMBER NOT NULL <br>
 &nbsp;  &nbsp; , INTEREST_AMOUNT NUMBER NOT NULL <br>
 &nbsp;  &nbsp; , FIRST_PAYMENT_DATE DATE <br>
 &nbsp;  &nbsp; , LAST_PAYMENT_DATE DATE <br>
 &nbsp;  &nbsp; , ACTION_DATE DATE DEFAULT sysdate NOT NULL <br>
 &nbsp;  &nbsp; , CONSTRAINT CREDIT_PK PRIMARY KEY <br>
  (<br>
 &nbsp;  &nbsp; &nbsp;  &nbsp;    ID <br>
  &nbsp;  &nbsp; )<br>
  &nbsp;  &nbsp; &nbsp;  &nbsp; ENABLE <br>
 &nbsp;  &nbsp;);<br><br>

ALTER TABLE CREDIT
ADD CONSTRAINT CREDIT_FK1 FOREIGN KEY
(
  CUSTOMER_ID 
)
REFERENCES CUSTOMER
(
  ID 
)
ENABLE;

COMMENT ON COLUMN CREDIT.CUSTOMER_ID IS 'musteri id';

COMMENT ON COLUMN CREDIT.HOME_PRICE IS 'evin AZN olaraq deyeri';

COMMENT ON COLUMN CREDIT.INITIAL_PAYMENT IS 'musterinin odediyi ilkin odenis meblegi';

COMMENT ON COLUMN CREDIT.CREDIT_AMOUNT IS 'bankin ayirdigi kredit meblegi';

COMMENT ON COLUMN CREDIT.INTEREST_AMOUNT IS 'musterinin toplam odediyi faiz meblegi';

COMMENT ON COLUMN CREDIT.FIRST_PAYMENT_DATE IS 'ilk odenis tarixi';

COMMENT ON COLUMN CREDIT.LAST_PAYMENT_DATE IS 'son odenis tarixi';

COMMENT ON COLUMN CREDIT.ACTION_DATE IS 'emeliyyat tarixi';

