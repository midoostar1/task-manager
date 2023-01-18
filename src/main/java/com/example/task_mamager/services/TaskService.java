package com.example.task_mamager.services;


import com.example.task_mamager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskDao;
}
