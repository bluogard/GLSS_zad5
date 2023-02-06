import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class Operations {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public void selectCustomer(Connection connection, String surname){

        final String QUERY = "SELECT * FROM customer WHERE surname=?";

        try{

            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1,surname);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                log.info("Dane uzytkownika: ");
                log.info(resultSet.getString("name"));
                log.info(resultSet.getString("surname"));
                log.info(resultSet.getString("address"));
                log.info(resultSet.getString("city"));
                log.info(resultSet.getString("phone"));
                log.info(resultSet.getString("country"));
                log.info(resultSet.getString("email"));

            }

        }catch (SQLException e){

            e.printStackTrace();

        }


    }

    public void updateCustomer(Connection connection, String surname, String phone){

        final String UPDATE = "UPDATE customer set phone=? WHERE surname=?";

        try{

            //UPDATE
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,phone);
            preparedStatement.setString(2,surname);
            preparedStatement.executeUpdate();


        }catch (SQLException e){

            e.printStackTrace();

        }

    }


    public void insertIntoCustomer(Connection connection,String name,String surname,String address, String city,String country, String phone, String email){

        final String INSERT = "INSERT INTO customer(name, surname, address, city,country, phone, email) VALUES(?,?,?,?,?,?,?)";


        try{

            //INSERT
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,city);
            preparedStatement.setString(5,country);
            preparedStatement.setString(6,phone);
            preparedStatement.setString(7,email);
            preparedStatement.executeUpdate();


        }catch (SQLException e){

            e.printStackTrace();

        }



    }

    public void deleteLoan(Connection connection, int id){

        final String QUERY = "DELETE FROM loan WHERE id_loan=?";

        try{


            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();


        }catch (SQLException e){

            e.printStackTrace();

        }


    }

    public void closeConnection(){

        try{preparedStatement.close();}catch(SQLException e){e.printStackTrace();}
        try{resultSet.close();}catch(SQLException e){e.printStackTrace();}

    }

}


