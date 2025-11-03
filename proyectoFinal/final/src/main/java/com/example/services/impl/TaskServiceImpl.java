package com.example.services.impl;

import com.example.repositories.TaskRepository;
import com.example.services.TaskService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.Task;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public Optional<Task> update(Long id, Task task) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()){
            Task taskDB = taskOptional.orElseThrow();

            taskDB.setDescription(task.getDescription());

            return Optional.of(taskRepository.save(taskDB));
        }
        return taskOptional;
    }

    @Transactional
    @Override
    public Optional<Task> delete(Long id) {
        Optional<Task> optional =taskRepository.findById(id);
        optional.ifPresent(p -> taskRepository.delete(p));
        return optional;
    }

    @Transactional
    @Override
    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }
    
}
