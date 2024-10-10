package com.example.todolist.repository;

import com.example.todolist.model.TaskDB;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends Neo4jRepository<TaskDB, Long> {
    Optional<TaskDB> findByNameTask(String nameTask);

    void deleteByNameTask(String nameTask);
}
