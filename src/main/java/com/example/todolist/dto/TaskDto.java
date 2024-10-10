package com.example.todolist.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

   /* @Id
    @GeneratedValue
    private String id;*/
    private String nameTask;

    @Size(min = 2, max = 500)
    private String description;
    private boolean done;
    private LocalDate expire;
}
