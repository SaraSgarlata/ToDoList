package com.example.todolist.controller;

import com.example.todolist.dto.TaskDto;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.model.TaskDB;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    //private final TaskRepository;

    @GetMapping("/search")
    public String searchTaskByName(@RequestParam("name") String name, Model model) throws BadRequestException {
        List<TaskDto> tasks = (List<TaskDto>) Collections.singletonList(taskService.getTaskByName(name));  // Utilizza il metodo del servizio
        model.addAttribute("tasks", tasks);  // Aggiungi i risultati al model
        return "task";  // Ritorna la vista della lista delle task
    }

    // Visualizza il form di creazione di una nuova task
    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new TaskDto());
        return "task-form";  // Restituisce "task-form.html"
    }

    // Gestisce la creazione di una nuova task
    @PostMapping("/new")
    public String createTask(@ModelAttribute TaskDto taskDto) {
        taskService.createNewTask(taskDto);
        return "redirect:/task/all";  // Redirige alla lista di tutte le task
    }

    /* Metodo per aggiornare lo stato "Completato" di una task
    public void updateTaskCompletion(String nameTask, boolean done) throws BadRequestException {
        Optional<TaskDB> taskOptional = taskRepository.findByNameTask(nameTask);

        if (taskOptional.isPresent()) {
            TaskDB task = taskOptional.get();
            task.setDone(done);  // Imposta lo stato completato (true o false)
            taskRepository.save(task);  // Salva l'aggiornamento nel database
        } else {
            throw new BadRequestException("Task not found with name: " + nameTask);
        }
    }

    @PostMapping("/complete/{name}")
    public String completeTask(@PathVariable String name, @RequestParam("done") boolean done) throws BadRequestException {
        taskService.updateTaskCompletion(name, done);
        return "redirect:/task/all";  // Dopo aver aggiornato, redirigi all'elenco delle task
    }
 */


    // Visualizza tutte le task
    @GetMapping("/all")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task";  // Restituisce "tasks.html"
    }

    // Cancella una task tramite il nome
    @PostMapping("/delete/name/{nameTask}")
    public String deleteTaskByName(@PathVariable String nameTask) throws BadRequestException {
        taskService.deleteTaskByName(nameTask);
        return "redirect:/task/all";  // Redirige alla lista di tutte le task
    }

    // Visualizza il form di modifica di una task
    @GetMapping("/edit/{nameTask}")
    public String editTaskForm(@PathVariable String nameTask, Model model) throws BadRequestException {
        TaskDto task = taskService.getTaskByName(nameTask);
        model.addAttribute("task", task);
        return "task-edit-form";  // Restituisce "task-edit-form.html"
    }

    // Gestisce la modifica di una task
    @PostMapping("/edit/{nameTask}")
    public String updateTask(@PathVariable String nameTask, @ModelAttribute TaskDto taskDto) throws BadRequestException {
        taskService.updateTask(nameTask, taskDto);
        return "redirect:/task/all";  // Redirige alla lista di tutte le task
    }

    // Visualizza i dettagli di una task
    @GetMapping("/details/{nameTask}")
    public String getTaskByName(@PathVariable String nameTask, Model model) throws BadRequestException {
        TaskDto task = taskService.getTaskByName(nameTask);
        model.addAttribute("task", task);
        return "task-details";  // Restituisce "task-details.html"
    }

    // Conferma eliminazione di una task
    @GetMapping("/delete/{nameTask}")
    public String deleteTaskConfirmation(@PathVariable String nameTask, Model model) throws BadRequestException {
        TaskDto task = taskService.getTaskByName(nameTask);
        model.addAttribute("task", task);
        return "delete-confirmation";  // Restituisce "delete-confirmation.html"
    }
}