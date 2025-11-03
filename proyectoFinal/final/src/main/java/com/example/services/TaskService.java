package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.models.Task;

public interface TaskService {
    Task create(Task task); //crear tarea
    List<Task> findAll(); //listar todas las tareas
    Optional<Task> findById(Long id);
    Optional<Task> update(Long id, Task task); //actualizar una tarea
    Optional<Task> delete(Long id);//eliminar tarea
}
