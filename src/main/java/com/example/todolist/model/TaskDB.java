package com.example.todolist.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


import java.time.LocalDate;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDB {

    @Id
    private Long id;

    private String nameTask;
    private String description;
    private boolean done;
    private LocalDate expire;
}
