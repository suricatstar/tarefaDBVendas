package br.com.andyano.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author anderson.salviano
 * Criado em: 15/10/2025
 */
public class ConnectionFactory {

    private static Connection connection;

    private ConnectionFactory(Connection connection){

    }

    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = initConnection();
        } else if(connection != null && connection.isClosed()){

            connection =  initConnection();
        }
        return connection;
    }

    public static Connection initConnection(){
        try{
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/exercicioMod29","postgres","andyano1234");

        }catch(SQLException e){
            throw  new RuntimeException(e);
        }
    }

}
