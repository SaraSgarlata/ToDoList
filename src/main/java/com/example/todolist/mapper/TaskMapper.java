package com.example.todolist.mapper;

import com.example.todolist.dto.TaskDto;
import com.example.todolist.model.TaskDB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // TaskDto a TaskDB
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nameTask", source ="nameTask" )
    @Mapping(target = "description",source ="description")
    @Mapping(target = "done",source ="done")
    @Mapping(target = "expire",source ="expire")
    TaskDB taskDtoToTaskDB(TaskDto taskDto);

    //  TaskDB a TaskDto

    @Mapping(target = "nameTask", source ="nameTask" )
    @Mapping(target = "description",source ="description")
    @Mapping(target = "done",source ="done")
    @Mapping(target = "expire",source ="expire")
    TaskDto taskDBToTaskDto(TaskDB taskDB);
}
