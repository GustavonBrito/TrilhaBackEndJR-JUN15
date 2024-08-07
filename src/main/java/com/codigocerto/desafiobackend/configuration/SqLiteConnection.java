package com.codigocerto.desafiobackend.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Logger;

@Configuration
@Data
public class SqLiteConnection {

    private Connection connection;

    Logger logger = Logger.getLogger(getClass().getName());

    public SqLiteConnection() {

        String url = "jdbc:sqlite:src/main/java/com/codigocerto/desafiobackend/sqlite_data/sqlite_data.db";
        // Especificação da URL do SQLITE

        try {

            connection = DriverManager.getConnection(url);

            logger.info("Connection Successful");

        } catch (SQLException e) {

            logger.info("Error Connecting to Database");

            e.printStackTrace();

        }

    }


    public void closeConnection() {

        try {

            if (connection != null) {
                logger.info("Connection Closed");

                connection.close();

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
