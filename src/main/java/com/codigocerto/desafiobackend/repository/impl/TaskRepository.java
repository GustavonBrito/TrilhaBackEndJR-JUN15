package com.codigocerto.desafiobackend.repository.impl;

import com.codigocerto.desafiobackend.configuration.SqLiteConnection;
import com.codigocerto.desafiobackend.entity.TaskEntity;
import com.codigocerto.desafiobackend.enums.Status;
import com.codigocerto.desafiobackend.repository.IMethodsToConnectToDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

@Repository
public class TaskRepository implements IMethodsToConnectToDB<TaskEntity> {

    SqLiteConnection db = new SqLiteConnection();

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public TaskEntity save(TaskEntity taskEntity) {
        Connection connection;
        PreparedStatement preparedStatement;
        TaskEntity taskEntityToReturnToService = new TaskEntity();
        Long id = null;
        try {
            connection = db.getConnection();
            if (connection != null) {

                Statement statement = connection.createStatement();

                String insertTask = "INSERT INTO task_entity (id ,name, description, status, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertTask);

                ResultSet resultSet = statement.executeQuery("SELECT * FROM task_entity");

                while (resultSet.next()){
                    id = resultSet.getLong("id");
                }

                if(id == null){
                    id = 0L;
                }

                preparedStatement.setLong(1,id + 1);
                preparedStatement.setString(2, taskEntity.getName());
                preparedStatement.setString(3, taskEntity.getDescription());
                preparedStatement.setString(4, String.valueOf(taskEntity.getStatus()));
                Timestamp createdAtTimestamp = Timestamp.from(Instant.now().atOffset(ZoneOffset.of("-03:00")).toInstant());
                Timestamp updatedAtTimestamp = Timestamp.from(Instant.now().atOffset(ZoneOffset.of("-03:00")).toInstant());
                preparedStatement.setTimestamp(5, createdAtTimestamp);
                preparedStatement.setTimestamp(6, updatedAtTimestamp);
                preparedStatement.executeUpdate();

                String queryToFindTaskCreated = "SELECT * FROM task_entity WHERE id = ?";

                PreparedStatement statementToFindTaskCreated = connection.prepareStatement(queryToFindTaskCreated);

                statementToFindTaskCreated.setLong(1,id + 1);

                ResultSet taskFinded = statementToFindTaskCreated.executeQuery();

                while(taskFinded.next()){
                    taskEntityToReturnToService.setId(taskFinded.getLong("id"));
                    taskEntityToReturnToService.setName(taskFinded.getString("name"));
                    taskEntityToReturnToService.setDescription(taskFinded.getString("description"));
                    taskEntityToReturnToService.setStatus(Status.valueOf(taskFinded.getString("status")));
                    taskEntityToReturnToService.setCreatedAt(taskFinded.getTimestamp("createdAt"));
                    taskEntityToReturnToService.setUpdatedAt(taskFinded.getTimestamp("updatedAt"));
                }
            }else {
                logger.info("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return taskEntityToReturnToService;
    }

    @Override
    public List<TaskEntity> findAll(Integer page) {
        Connection connection;
        PreparedStatement preparedStatement;
        TaskEntity taskEntity;
        List<TaskEntity> taskList = new ArrayList<>();
        try {
            connection = db.getConnection();
            if (connection != null){
                String allTasksFinded = "SELECT * FROM task_entity LIMIT 5 OFFSET ?";

                preparedStatement = connection.prepareStatement(allTasksFinded);
                preparedStatement.setInt(1, page * 5);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    taskEntity = new TaskEntity();
                    taskEntity.setId(resultSet.getLong("id"));
                    taskEntity.setName(resultSet.getString("name"));
                    taskEntity.setDescription(resultSet.getString("description"));
                    taskEntity.setStatus(Status.valueOf(resultSet.getString("status")));
                    taskEntity.setCreatedAt(resultSet.getTimestamp("createdAt"));
                    taskEntity.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                    taskList.add(taskEntity);
                }
            }else{
                logger.info("Connection Failed!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public TaskEntity findById(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        TaskEntity taskEntity = new TaskEntity();
        try {
            connection = db.getConnection();
            if (connection != null){
                String taskFoundById = "SELECT * FROM task_entity WHERE id = ?";

                preparedStatement = connection.prepareStatement(taskFoundById);
                preparedStatement.setLong(1,id);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    taskEntity.setId(resultSet.getLong("id"));
                    taskEntity.setName(resultSet.getString("name"));
                    taskEntity.setDescription(resultSet.getString("description"));
                    taskEntity.setStatus(Status.valueOf(resultSet.getString("status")));
                    taskEntity.setCreatedAt(resultSet.getTimestamp("createdAt"));
                    taskEntity.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                }
            }else{
                logger.info("Connection Failed!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return taskEntity;
    }

    @Override
    public TaskEntity updateById(Long id, TaskEntity taskEntity) {
        Connection connection;
        PreparedStatement preparedStatement;
        TaskEntity taskUpdatedToReturn = new TaskEntity();
        try {
            connection = db.getConnection();
            if (connection != null) {
                String taskUpdatedById = "UPDATE task_entity SET name = ?, description = ?, status = ? ,updatedAt = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(taskUpdatedById);

                preparedStatement.setString(1,taskEntity.getName());
                preparedStatement.setString(2, taskEntity.getDescription());
                preparedStatement.setString(3, String.valueOf(taskEntity.getStatus()));
                preparedStatement.setTimestamp(4, Timestamp.from(Instant.now()));
                preparedStatement.setLong(5,id);

                preparedStatement.executeUpdate();

                String taskUpdated = "SELECT * FROM task_entity WHERE id = ?";

                preparedStatement = connection.prepareStatement(taskUpdated);

                preparedStatement.setLong(1,id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    taskUpdatedToReturn.setId(resultSet.getLong("id"));
                    taskUpdatedToReturn.setName(resultSet.getString("name"));
                    taskUpdatedToReturn.setDescription(resultSet.getString("description"));
                    taskUpdatedToReturn.setStatus(Status.valueOf(resultSet.getString("status")));
                    taskUpdatedToReturn.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                    taskUpdatedToReturn.setCreatedAt(resultSet.getTimestamp("createdAt"));
                }
            }else{
                logger.info("Connection failed");
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return taskUpdatedToReturn;
    }

    @Override
    public void deleteById(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = db.getConnection();
            if (connection != null) {

                String taskDeletedFromBD = "DELETE FROM task_entity WHERE id = ?";

                preparedStatement = connection.prepareStatement(taskDeletedFromBD);
                preparedStatement.setLong(1,id);
                preparedStatement.executeUpdate();
            }else {
                logger.info("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
