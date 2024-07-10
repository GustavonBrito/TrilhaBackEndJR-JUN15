package com.codigoCerto.desafioBackEnd.configuration;

// Uncomment line 3 to import all classes from java.sql.* for database operations.

//import java.sql.*;

// Alternatively, import only the necessary classes.

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@Data
public class SqLiteConnection {

    private Connection connection;


    public SqLiteConnection() {

        String url = "jdbc:sqlite:sqlite_data/sqlite_data.db"; // Especificação da URL do SQLITE

        try {

            connection = DriverManager.getConnection(url);

            System.out.println("Connection Successful");

        } catch (SQLException e) {

            System.out.println("Error Connecting to Database");

            e.printStackTrace();

        }

    }


    public void closeConnection() {

        try {

            if (connection != null) {
                System.out.println("Connection Closed");

                connection.close();

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
