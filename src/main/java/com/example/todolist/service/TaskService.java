package com.example.todolist.service;

import com.example.todolist.dto.TaskDto;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.model.TaskDB;
import com.example.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

   private final TaskRepository taskRepository;
   private final TaskMapper taskMapper;

    public TaskDto createNewTask(TaskDto taskDto) {
        TaskDB newTask = taskMapper.taskDtoToTaskDB(taskDto);
        TaskDB savedTask = taskRepository.save(newTask);
        return taskMapper.taskDBToTaskDto(savedTask);
    }

    //trovare tutte le task
    public List<TaskDto> findAll() {
        List<TaskDB> tasks = taskRepository.findAll(); //prendo tutti i task
        List<TaskDto> taskDtoLista= new ArrayList<>(); //creo un array di taskDTO
        for (TaskDB task : tasks) { //itero i task e li trasformo in task DTO
            TaskDto taskMap = taskMapper.taskDBToTaskDto(task);
            taskDtoLista.add(taskMap); //insrisco il taskdto nella lista
        }
        return taskDtoLista;
    }

    public void deleteTaskByName(String nameTask) throws BadRequestException {
        if (nameTask == null || nameTask.length()<1) {
            throw new BadRequestException("Il nome della task non è valido o è vuoto.");
        }
        Optional<TaskDB> taskOptional = taskRepository.findByNameTask(nameTask);  // Cerca tramite nome
        if (taskOptional.isPresent()) {
            taskRepository.deleteByNameTask(nameTask); // Elimina la task trovata
        } else {
            throw new BadRequestException("La task con nome " + nameTask + " non esiste.");
        }
    }

    public TaskDto updateTask(String nameTask, TaskDto taskDto) throws BadRequestException {
        if (nameTask == null || nameTask.isEmpty()) {
            throw new BadRequestException("Nome task non valido o non esuistente");
        }

        Optional<TaskDB> taskOptional = taskRepository.findByNameTask(nameTask);
        if (taskOptional.isPresent()) {
            TaskDB taskDB = taskMapper.taskDtoToTaskDB(taskDto);
            taskRepository.deleteByNameTask(nameTask);
            taskRepository.save(taskDB);
        } else {
            throw new BadRequestException("La task con nome " + nameTask + " non esiste.");
        }
        return taskDto;
    }


    public TaskDto getTaskByName(String nameTask) throws BadRequestException {
        if (nameTask == null || nameTask.isEmpty()) {
            throw new BadRequestException("task is not exist");
        }
        Optional<TaskDB> taskOptional = taskRepository.findByNameTask(nameTask);
        return taskOptional.map(taskMapper::taskDBToTaskDto).orElse(null);
    }

   /* public void updateTaskCompletion(String nameTask, boolean done) throws BadRequestException {
        Optional<TaskDB> taskOptional = taskRepository.findByNameTask(nameTask);

        if (taskOptional.isPresent()) {
            TaskDB task = taskOptional.get();
            task.setDone(done);  // Imposta lo stato completato (true o false)
            taskRepository.save(task);  // Salva l'aggiornamento nel database
        } else {
            throw new BadRequestException("Task not found with name: " + nameTask);
        }
    }

    */

}


