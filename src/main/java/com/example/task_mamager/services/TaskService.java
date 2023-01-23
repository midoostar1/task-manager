package com.example.task_mamager.services;


import com.example.task_mamager.models.Task;
import com.example.task_mamager.models.User;
import com.example.task_mamager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskDao;

    public List<Task> findByOwner(User user) {
        return taskDao.findByOwner(user);
    }

    public void save(Task task) {
        taskDao.save(task);
    }

    public Task findById(long id) {
        return taskDao.findById(id).get();
    }

    public void delete(Task task) {
        taskDao.delete(task);
    }

}
