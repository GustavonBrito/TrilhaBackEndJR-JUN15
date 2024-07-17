package com.codigoCerto.desafioBackEnd.repository.impl;

import com.codigoCerto.desafioBackEnd.configuration.SqLiteConnection;
import com.codigoCerto.desafioBackEnd.entity.TaskEntity;
import com.codigoCerto.desafioBackEnd.enums.Status;
import com.codigoCerto.desafioBackEnd.repository.IMethodsToConnectToDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Repository
public class TaskRepository implements IMethodsToConnectToDB<TaskEntity> {

    SqLiteConnection db = new SqLiteConnection();

    @Override
    public TaskEntity save(TaskEntity taskEntity) {
        Connection connection;
        PreparedStatement preparedStatement;
        TaskEntity taskEntityToReturnToService = new TaskEntity();
        Long id = null;
        try {
            connection = db.getConnection();
            if (connection != null) {

                String createTableTask = "CREATE TABLE IF NOT EXISTS task_entity (id LONG PRIMARY KEY, name VARCHAR(100) NOT NULL,description VARCHAR(100) NOT NULL,status VARCHAR(100) NOT NULL, createdAt DATETIME, updatedAt DATETIME)";

                Statement statement = connection.createStatement();
                statement.executeUpdate(createTableTask);

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
                System.out.println("Connection Failed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return taskEntityToReturnToService;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public TaskEntity findById(Long id) {
        return null;
    }

    @Override
    public TaskEntity updateById(Long id, TaskEntity taskEntity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
