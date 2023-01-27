package com.example.task_mamager.controllers;

import com.example.task_mamager.models.Category;
import com.example.task_mamager.models.Task;
import com.example.task_mamager.models.User;
import com.example.task_mamager.services.CategoryService;
import com.example.task_mamager.services.TaskService;
import com.example.task_mamager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Controller
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;
    private final CategoryService categoryService;

    public User loggedInUser() {

       User user1 =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       User user = userService.findById(user1.getId());
       return user;
    }


    // ***************************show all tasks***********************

    @GetMapping("/task")
    public String showTasks(Model model) {
        User user = loggedInUser();

        List<Task> tasks = taskService.findByOwner(user);


        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);


        return "tasks";
    }


    // ***************************Create Task ***********************
    @GetMapping("/showTaskForm")
    public String ShowCreate() {

        return "createTask";
    }


    @PostMapping("/createTask")
    public String create(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category) {

        User user = loggedInUser();
        Category category1 = categoryService.findByName(category);
        LocalDate createdDate = LocalDate.now();

        Task task = new Task(name, description, false, false, category1, createdDate, user);
        taskService.save(task);

        return "redirect:/task";
    }


//**********************************Delete Task*******************************

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") long id) {

        Task task = taskService.findById(id);
        taskService.delete(task);
        return "redirect:/task";
    }


//***************************Mark task as completed*******************************************

    @GetMapping("/completeTask/{id}")
    public String completeTask(@PathVariable("id") long id) {
        Task task = taskService.findById(id);
        if(task.isScheduled()){
            task.setScheduled(false);
            task.setDueDate(null);
        }
        task.setCompleted(true);
        taskService.save(task);
        return "redirect:/task";
    }


    //    *********************************Schedule task********************************
    @GetMapping("/scheduleTask/{id}")
    public String showScheduleForm(@PathVariable("id") long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);

        return "scheduleTask";
    }


    @PostMapping("/scheduleTask")
    public String schedule(
            @RequestParam("date") LocalDate date,
            @RequestParam("id") long id,
            Model model) {


        Task task = taskService.findById(id);
        if(date.isBefore(LocalDate.now())){
            model.addAttribute("task", task);
          model.addAttribute("pastDate","Due date cannot be in the past.");
          return "scheduleTask";
        }

        task.setDueDate(date);
        task.setScheduled(true);
        taskService.save(task);

        return "redirect:/task";
    }




//    *******************************Update Task**************************************

    @GetMapping("/updateTask/{id}")
    public String showUpdateTaskForm(@PathVariable("id") long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }


    @PostMapping("/updateTask")
    public String updateTask(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("category") String category,
                           @RequestParam(name ="date",required=false) Optional<LocalDate> date,
                             @RequestParam("id") long id,
                             Model model) {


Task task = taskService.findById(id);



Category category1 = categoryService.findByName(category);
task.setName(name);
task.setDescription(description);
task.setCategory(category1);
        date.ifPresent(task::setDueDate);
taskService.save(task);

        return "redirect:/task";
    }



//    *******************************Sort Task By category****************************************

@GetMapping("/category/{category}")
    public String taskCategory(@PathVariable("category")String category,
                               Model model){
        User user = loggedInUser();
        Category category1 = categoryService.findByName(category);
      List<Task> tasks = taskService.findByCategory(category1,user);
      model.addAttribute("tasks",tasks);
    model.addAttribute("user",user);
    if (tasks.size()==0){
        model.addAttribute("notask","You Dont Have any task in this category.");
    }

    return "tasks";
    }



    @GetMapping("/status/{status}")
    public String taskStatus(@PathVariable("status")String status,
                             Model model){
        User user = loggedInUser();
        model.addAttribute("user",user);

        if(status.equals("scheduled")) {
            List<Task> tasks = taskService.findByScheduled(true,user);
            model.addAttribute("tasks",tasks);

            if (tasks.size()==0){
                model.addAttribute("notask","You Dont Have any task with this status.");
            }


        }

        if(status.equals("completed")) {
            List<Task> tasks = taskService.findByCompleted(true,user);
            model.addAttribute("tasks",tasks);

            if (tasks.size()==0){
                model.addAttribute("notask","You Dont Have any task with this status.");
            }

        }

        if(status.equals("pending")) {
            List<Task> tasks = taskService.findByCompletedAndScheduled(false, false, user);
            model.addAttribute("tasks", tasks);

            if (tasks.size() == 0) {
                model.addAttribute("notask", "You Dont Have any task with this status.");
            }
        }



        return "tasks";
    }




}
