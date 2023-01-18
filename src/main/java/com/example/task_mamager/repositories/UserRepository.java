package com.example.task_mamager.repositories;

import com.example.task_mamager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Object findByUsername(String username);
}
