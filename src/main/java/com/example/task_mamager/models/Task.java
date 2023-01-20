package com.example.task_mamager.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private boolean IsCompleted;
    private boolean IsScheduled;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_on")
    private LocalDate dateCreated;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Task(String name, String description, boolean isCompleted, boolean isScheduled, Category category, LocalDate dateCreated, User owner) {
        this.name = name;
        this.description = description;
        IsCompleted = isCompleted;
        IsScheduled = isScheduled;
        this.category = category;
        this.dateCreated = dateCreated;
        this.owner = owner;
    }
}
