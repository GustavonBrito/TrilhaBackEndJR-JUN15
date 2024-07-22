package com.codigoCerto.desafioBackEnd.repository.impl;

import com.codigoCerto.desafioBackEnd.configuration.SqLiteConnection;
import com.codigoCerto.desafioBackEnd.entity.TaskUserEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneOffset;

@Repository
public class TaskUserRepository {
    SqLiteConnection db = new SqLiteConnection();

    public TaskUserEntity save(TaskUserEntity taskUserEntity) {
        Connection connection;
        PreparedStatement preparedStatement;
        TaskUserEntity taskUserEntityToReturnToService = new TaskUserEntity();
        Long id = null;
        try {
            connection = db.getConnection();
            if (connection != null) {

                Statement statement = connection.createStatement();

                String insertTask = "INSERT INTO task_user (id ,taskId,userId,createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertTask);

                ResultSet resultSet = statement.executeQuery("SELECT * FROM task_user");

                while (resultSet.next()){
                    id = resultSet.getLong("id");
                }

                if(id == null){
                    id = 0L;
                }

                preparedStatement.setLong(1,id + 1);
                preparedStatement.setLong(2, taskUserEntity.getTaskId());
                preparedStatement.setLong(3, taskUserEntity.getUserId());
                Timestamp createdAtTimestamp = Timestamp.from(Instant.now().atOffset(ZoneOffset.of("-03:00")).toInstant());
                Timestamp updatedAtTimestamp = Timestamp.from(Instant.now().atOffset(ZoneOffset.of("-03:00")).toInstant());
                preparedStatement.setTimestamp(4, createdAtTimestamp);
                preparedStatement.setTimestamp(5, updatedAtTimestamp);
                preparedStatement.executeUpdate();

                String queryToFindTaskUserCreated = "SELECT * FROM task_user WHERE id = ?";

                PreparedStatement statementToFindTaskUserCreated = connection.prepareStatement(queryToFindTaskUserCreated);

                statementToFindTaskUserCreated.setLong(1,id + 1);

                ResultSet taskUserFound = statementToFindTaskUserCreated.executeQuery();

                while(taskUserFound.next()){
                    taskUserEntityToReturnToService.setId(taskUserFound.getLong("id"));
                    taskUserEntityToReturnToService.setUserId(taskUserFound.getLong("userId"));
                    taskUserEntityToReturnToService.setTaskId(taskUserFound.getLong("taskId"));
                    taskUserEntityToReturnToService.setCreatedAt(taskUserFound.getTimestamp("createdAt"));
                    taskUserEntityToReturnToService.setUpdatedAt(taskUserFound.getTimestamp("updatedAt"));
                }
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return taskUserEntityToReturnToService;
    }
}
