import oracle.jdbc.proxy.annotation.Pre;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;


public class Database {
    private static long customer_id;
    private static long credit_id;

    private final static String connectionURL = "jdbc:oracle:thin:@192.168.0.104:1521/ORCL";
    private final static String username = "hr";   // Database connection username
    private final static String password = "hr";  // Database connection password
    private static Connection conn = null;
    private static PreparedStatement statement = null;
    private static ResultSet rs=null;

    public static void createCustomer(String name, String surname, LocalDate bornLocalDate) {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection(connectionURL, username, password);
            conn.setAutoCommit(false);
            System.out.println("Successfuly connected to database");
            String query = "insert into customer values (customer_seq.nextval,?,?,?) ";
            statement = conn.prepareStatement(query, new String[]{"id"});
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setDate(3, java.sql.Date.valueOf(bornLocalDate));
            int result = statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            rs.next();
            Database.customer_id = rs.getInt(1);

            System.out.println(name + " " + surname + " costumer has been created");
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Credit has been created");
                try {
                    if(statement!=null)statement.close();
                    if(rs!=null) rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

public static void createCredit(BigDecimal homePrice,BigDecimal initialPayment,BigDecimal creditAmount,BigDecimal interestAmount,
                                                                            LocalDate firstDate,LocalDate lastDate){
        try{
        String query = "insert into credit (id,customer_id,home_price,initial_payment,credit_amount,interest_amount," +
                "first_payment_date,last_payment_date) values (credit_seq.nextval,?,?,?,?,?,?,?)";
            statement = conn.prepareStatement(query, new String[]{"id"});
            statement.setLong(1,Database.customer_id);
            statement.setBigDecimal(2, homePrice);
            statement.setBigDecimal(3, initialPayment);
            statement.setBigDecimal(4, creditAmount);
            statement.setBigDecimal(5,interestAmount);
            statement.setDate(6,java.sql.Date.valueOf(firstDate));
            statement.setDate(7,java.sql.Date.valueOf(lastDate));
            statement.executeUpdate();
        rs=statement.getGeneratedKeys();
        if(rs.next()){
            Database.credit_id=rs.getLong(1);
        }
        conn.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(statement!=null)statement.close();
                if(rs!=null) rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
}


    public static void createMonthlyPayment(BigDecimal amonthlyPayment, LocalDate time, BigDecimal monthlyPayment, BigDecimal monthlyInterestAmount) {
        try {
            String query = "insert into monthly_payment values (monthly_payment_seq.nextval,?,?,?,?,?) ";
            statement = conn.prepareStatement(query);
            statement.setLong(1,Database.credit_id);
            statement.setDate(2, java.sql.Date.valueOf(time));
            statement.setBigDecimal(3, amonthlyPayment);
            statement.setBigDecimal(4, monthlyInterestAmount);
            statement.setBigDecimal(5, monthlyPayment);
            statement.addBatch();
            statement.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(statement!=null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void  closeConnection(){
        try{
            if(conn!=null) conn.close();
            if(statement!=null) statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

