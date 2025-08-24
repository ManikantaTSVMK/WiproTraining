package com.sample.controller;

import com.sample.model.Task;
import com.sample.model.User;
import com.sample.service.TaskService;
import com.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "admin/manageUsers";
    }

    @PostMapping("/users/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "admin/manageUsers"; // redisplay form with errors
        }

        if (user.getId() == null) {
            user.setRole("USER");
            userService.registerUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/admin/users";
    }


    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/allocate")
    public String showAllocateTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.getAllUsers());
        return "admin/allocateTask";
    }

    @PostMapping("/allocate/save")
    public String saveTask(@ModelAttribute("task") Task task) {
        task.setStatus("Pending");
        taskService.createTask(task);
        return "redirect:/admin/dashboard";
    }
}
