package com.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Task;
import com.example.services.TaskService;
import com.example.utils.UtilCrud;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/task")
@Tag(
    name="Tasks",
    description ="Manejador en operaciones de las tareas"
)
public class TaskController {
   private TaskService taskService;

   public TaskController(TaskService taskService) {
    this.taskService = taskService;
   }

   @Operation(summary = "Get all tasks", description = "Se muestra la descripcion de todas las tareas existentes")
   @GetMapping
   public List<Task> list() {
       return taskService.findAll();
   }

   @Operation(summary = "Get task by id", description = "Se muestra la descripcion de una tarea por ID")
   @GetMapping("/{id}")
   public ResponseEntity<?> findById(@PathVariable Long id){
       Optional<Task> taskOptional = taskService.findById(id);
       if (taskOptional.isPresent()) {
           return ResponseEntity.ok(taskOptional.orElseThrow());
        }else
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Create new task", description = "Se crea una tarea nueva")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Task task, 
            BindingResult result){
        if (result.hasFieldErrors()) {
            System.out.println(result);
            return UtilCrud.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(taskService.create(task));
    }

    @Operation(summary = "Update existing task", description = "Se actualiza una tarea ya existente")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Task task,
    BindingResult result, @PathVariable Long id) {
        if(result.hasFieldErrors()){
            return UtilCrud.validation(result);
        }
        Optional<Task> optional = taskService.update(id, task);
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Delete existing task", description = "Se elimina una tarea existente")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Task> optional = taskService.delete(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(optional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
