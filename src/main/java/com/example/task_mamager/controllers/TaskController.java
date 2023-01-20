package com.example.task_mamager.controllers;

import com.example.task_mamager.models.Category;
import com.example.task_mamager.models.Task;
import com.example.task_mamager.models.User;
import com.example.task_mamager.services.CategoryService;
import com.example.task_mamager.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Controller
public class TaskController {

 private final TaskService taskService;

    private final CategoryService categoryService;

    public User loggedInUser(){
        return    (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



    // ***************************show all tasks***********************

    @GetMapping("/task")
    public String showTasks(Model model){
        User user = loggedInUser();

        List<Task> tasks = taskService.findByOwner(user);


model.addAttribute("tasks", tasks);
        model.addAttribute("user",user);



        return "tasks";
    }



    // ***************************Create Task ***********************
    @GetMapping("/showTaskForm")
public String ShowCreate(){

        return "createTask";
}


@PostMapping("/createTask")
public String create(
        @RequestParam("name")String name,
        @RequestParam("description")String description,
        @RequestParam("category")String category){

    User user = loggedInUser();
    Category category1 = categoryService.findByName(category);
    LocalDate createdDate = LocalDate.now();

 Task task = new Task(name,description,false,false,category1,createdDate,user);
 taskService.save(task);

        return "redirect:/task";
}





//**********************************Delete Task*******************************

    @GetMapping("/deleteTask/{id}")
public String deleteTask(@PathVariable("id") long id){

       Task task = taskService.findById(id);
        taskService.delete(task);
        return "redirect:/task";
}


//***************************Mark task as completed*******************************************

    @GetMapping("/completeTask/{id}")
    public String completeTask(@PathVariable("id")long id){
        Task task = taskService.findById(id);
        task.setIsCompleted(true);
        taskService.save(task);
        return "redirect:/task";
    }
}
