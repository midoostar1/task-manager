package com.example.task_mamager.services;


import com.example.task_mamager.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryDao;

}
