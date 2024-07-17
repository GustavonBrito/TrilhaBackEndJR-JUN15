package com.codigoCerto.desafioBackEnd.repository.impl;

import com.codigoCerto.desafioBackEnd.configuration.SqLiteConnection;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
import com.codigoCerto.desafioBackEnd.repository.IMethodsToConnectToDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IMethodsToConnectToDB<UserEntity> {

    SqLiteConnection db = new SqLiteConnection();

    @Override
    public UserEntity save (UserEntity userEntity){
        Connection connection;
        PreparedStatement preparedStatement;
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

                String queryToFindUserCreated = "SELECT * FROM user_entity WHERE id = ?";

                PreparedStatement statementToFindUserCreated = connection.prepareStatement(queryToFindUserCreated);

                statementToFindUserCreated.setLong(1,id + 1);

                ResultSet userFinded = statementToFindUserCreated.executeQuery();

                while(userFinded.next()){
                    userToReturn.setId(userFinded.getLong("id"));
                    userToReturn.setName(userFinded.getString("name"));
                    userToReturn.setEmail(userFinded.getString("email"));
                    userToReturn.setCreatedAt(userFinded.getTimestamp("createdAt"));
                    userToReturn.setUpdatedAt(userFinded.getTimestamp("updatedAt"));
                }
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userToReturn;
    }

    @Override
    public List<UserEntity> findAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        UserEntity userEntity;
        List<UserEntity> userList = new ArrayList<>();
        try {
            connection = db.getConnection();
            if (connection != null){
                String allUsersFinded = "SELECT * FROM user_entity";

                preparedStatement = connection.prepareStatement(allUsersFinded);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    userEntity = new UserEntity();
                    userEntity.setId(resultSet.getLong("id"));
                    userEntity.setName(resultSet.getString("name"));
                    userEntity.setEmail(resultSet.getString("email"));
                    userEntity.setCreatedAt(resultSet.getTimestamp("createdAt"));
                    userEntity.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                    userList.add(userEntity);
                }
            }else{
                System.out.println("Connection Failed!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public UserEntity findById(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        UserEntity userEntity = new UserEntity();
        try {
            connection = db.getConnection();
            if (connection != null){
                String userFindedById = "SELECT * FROM user_entity WHERE id = ?";

                preparedStatement = connection.prepareStatement(userFindedById);
                preparedStatement.setLong(1,id);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    userEntity.setId(resultSet.getLong("id"));
                    userEntity.setName(resultSet.getString("name"));
                    userEntity.setEmail(resultSet.getString("email"));
                    userEntity.setCreatedAt(resultSet.getTimestamp("createdAt"));
                    userEntity.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                }
            }else{
                System.out.println("Connection Failed!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return userEntity;
    }

    public UserEntity findByEmail(String email) {
        Connection connection;
        PreparedStatement preparedStatement;
        UserEntity userEntity = new UserEntity();
        try {
            connection = db.getConnection();
            if (connection != null){
                String userFindedByEmail = "SELECT * FROM user_entity WHERE email = ?";

                preparedStatement = connection.prepareStatement(userFindedByEmail);
                preparedStatement.setString(1,email);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    userEntity.setEmail(resultSet.getString("email"));
                }
            }else{
                System.out.println("Connection Failed!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return userEntity;
    }

    @Override
    public UserEntity updateById(Long id, UserEntity userEntity){
        Connection connection;
        PreparedStatement preparedStatement;
        UserEntity userUpdatedToReturn = new UserEntity();
        try {
            connection = db.getConnection();
            if (connection != null) {
                String userUpdatedById = "UPDATE user_entity SET name = ?, email = ?, updatedAt = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(userUpdatedById);

                preparedStatement.setString(1,userEntity.getName());
                preparedStatement.setString(2, userEntity.getEmail());
                preparedStatement.setTimestamp(3, Timestamp.from(Instant.now()));
                preparedStatement.setLong(4,id);

                preparedStatement.executeUpdate();

                String userUpdated = "SELECT * FROM user_entity WHERE id = ?";

                preparedStatement = connection.prepareStatement(userUpdated);

                preparedStatement.setLong(1,id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    userUpdatedToReturn.setId(resultSet.getLong("id"));
                    userUpdatedToReturn.setName(resultSet.getString("name"));
                    userUpdatedToReturn.setEmail(resultSet.getString("email"));
                    userUpdatedToReturn.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                    userUpdatedToReturn.setCreatedAt(resultSet.getTimestamp("createdAt"));
                }
            }else{
                System.out.println("Connection failed");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return userUpdatedToReturn;
    }

    @Override
    public void deleteById(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = db.getConnection();
            if (connection != null) {

                String userDeletedFromBD = "DELETE FROM user_entity WHERE id = ?";

                preparedStatement = connection.prepareStatement(userDeletedFromBD);
                preparedStatement.setLong(1,id);
                preparedStatement.executeUpdate();
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}