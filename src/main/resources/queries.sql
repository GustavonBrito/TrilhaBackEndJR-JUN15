CREATE TABLE IF NOT EXISTS user_entity (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    confirmedPassword VARCHAR(100) NOT NULL,
    createdAt DATETIME,
    updatedAt DATETIME
);
CREATE TABLE IF NOT EXISTS task_entity (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    createdAt DATETIME,
    updatedAt DATETIME
);
CREATE TABLE IF NOT EXISTS task_user(
    id BIGINT PRIMARY KEY,
    taskId BIGINT,
    userId BIGINT,
    CONSTRAINT fkTask FOREIGN KEY (taskId) REFERENCES task_entity(id),
    CONSTRAINT fkUser FOREIGN KEY (userId) REFERENCES user_entity(id)
);



