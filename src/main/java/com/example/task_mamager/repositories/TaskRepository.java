package com.example.task_mamager.repositories;

import com.example.task_mamager.models.Task;
import com.example.task_mamager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOwner(User user);
}
