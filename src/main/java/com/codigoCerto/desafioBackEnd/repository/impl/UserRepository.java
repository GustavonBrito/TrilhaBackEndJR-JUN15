package com.codigoCerto.desafioBackEnd.repository.impl;

import com.codigoCerto.desafioBackEnd.configuration.SqLiteConnection;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
import com.codigoCerto.desafioBackEnd.repository.IMethodsToConnectToDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.List;

@Repository
public class UserRepository implements IMethodsToConnectToDB<UserEntity> {

    SqLiteConnection db = new SqLiteConnection();

    @Override
    public UserEntity save(UserEntity userEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        UserEntity userToReturn = new UserEntity();
        Long id = null;
        try {
        connection = db.getConnection();
            if (connection != null) {

                    String createTableUser = "CREATE TABLE IF NOT EXISTS user_entity (id LONG PRIMARY KEY, name VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, confirmedPassword VARCHAR(100) NOT NULL, createdAt DATETIME, updatedAt DATETIME)";

                    Statement statement = connection.createStatement();
                    statement.executeUpdate(createTableUser);

                    String insertUser = "INSERT INTO user_entity (id ,name, email, password, confirmedPassword, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(insertUser);

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM user_entity");

                    while (resultSet.next()){
                        id = resultSet.getLong("id");
                    }

                    if(id == null){
                        id = 0L;
                    }

                    preparedStatement.setLong(1,id + 1);
                    preparedStatement.setString(2, userEntity.getName());
                    preparedStatement.setString(3, userEntity.getEmail());
                    preparedStatement.setString(4, userEntity.getPassword());
                    preparedStatement.setString(5, userEntity.getConfirmedPassword());
                    Timestamp createdAtTimestamp = Timestamp.from(Instant.now());
                    Timestamp updatedAtTimestamp = Timestamp.from(Instant.now());
                    preparedStatement.setTimestamp(6, createdAtTimestamp);
                    preparedStatement.setTimestamp(7, updatedAtTimestamp);
                    preparedStatement.executeUpdate();
                    return userEntity;
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public UserEntity findById(Long id) {
        return null;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return null;
    }

    @Override
    public UserEntity deleteById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        UserEntity userToReturn = new UserEntity();
        try {
            connection = db.getConnection();
            if (connection != null) {

                String userDeletedFromBD = "DELETE FROM user_entity WHERE id = ?";

                preparedStatement = connection.prepareStatement(userDeletedFromBD);
                preparedStatement.setLong(1,id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User with ID " + id + " was deleted successfully.");
                    userToReturn.setId(id);
                } else {
                    System.out.println("No user found with ID " + id);
                }
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
//    public void DBConnection () {
//        if (connection != null) {
//
//            try {
//
//                Statement statement = connection.createStatement();
//
//                String createTable="CREATE TABLE IF NOT EXISTS user-entity (id INTEGER PRIMARY KEY, name TEXT)";
//
//                // Create table if not exists
//
////                Operation:C
//
//                statement.executeUpdate(createTable);
//
//
//                // Insert data
//
//                statement.executeUpdate("INSERT INTO students (name) VALUES (‘John’)");
//
//                System.out.println("Data Inserted");
//
//
//                // Display data
//
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
//
//
////                Operation:U
//
//                // Update data
//
////                statement.executeUpdate("UPDATE students SET name = ‘Johnny’ WHERE id = 1");
//
////                System.out.println(“Data Updated”);
//
//                // Display updated data
//
////                resultSet = statement.executeQuery(“SELECT * FROM students”);
//
////                System.out.println(“ID\tName”);
////
////                while (resultSet.next()) {
////
////                    int id = resultSet.getInt(“id”);
////
////                    String name = resultSet.getString(“name”);
////
////                    System.out.println(id + “\t“ + name);
////
////                }
//
//
////                Operation:D
//
////                 Delete data
//
//                statement.executeUpdate("DELETE FROM students WHERE id = 2");
//
//
//                // Close resources
//
//                resultSet.close();
//
//                statement.close();
//
//            } catch (SQLException e) {
//
//                e.printStackTrace();
//
//            } finally {
//
//                db.closeConnection();
//
//            }
//
//        } else {
//
//            System.out.println("Connection failed");
//
//        }


