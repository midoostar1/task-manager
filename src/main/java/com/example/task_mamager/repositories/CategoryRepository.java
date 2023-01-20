package com.example.task_mamager.repositories;

import com.example.task_mamager.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Category findByName(String name);
}
