package com.example.task_mamager.services;


import com.example.task_mamager.models.Category;
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

    public List<Task> findByCategory(Category category,User user) {
        return taskDao.findByCategoryAndOwner(category,user);
    }

public List<Task>findByScheduled(boolean b, User user){
        return taskDao.findByIsScheduledAndOwner(b, user);
}

    public List<Task> findByCompleted(boolean b, User user) {
        return taskDao.findByIsCompletedAndOwner(b, user);
    }


  public   List<Task> findByCompletedAndScheduled(boolean b, boolean b1,User user){
        return taskDao.findByIsCompletedAndIsScheduledAndOwner(b,b1,user);
    }

    public Task findByNameAndUser(String name, User user) {
        return taskDao.findByNameAndOwner(name, user);
    }
}
