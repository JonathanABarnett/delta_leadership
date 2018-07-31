package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.Attendee;
import com.jonathanbarnett.delta_leadership.models.Task;
import com.jonathanbarnett.delta_leadership.models.TaskType;
import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private TaskTypeService taskTypeService;

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/addTask")
    public String addTask(Model model) {
        model.addAttribute("title", "Add Task");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("attendeeList", attendeeService.findAll());
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("taskType", taskTypeService.findAll());

        model.addAttribute("task", new Task());


        return "addTask";
    }

    @PostMapping(value = "/addTask")
    public String processAddTask(Model model, @ModelAttribute("task") Task task, @ModelAttribute("completeBy") String date,
                                 @ModelAttribute("attendees") List<Attendee> attendees, @ModelAttribute("taskOwners") List<User> users,
                                 @ModelAttribute("taskTypes") List<TaskType> taskTypes, Principal principal)
    throws Exception{
        String user = principal.getName();

        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        task.setCompleteBy(date1);
        task.setAddedOn(new Date());
        task.setAddedBy(user);

        taskService.save(task);

        model.addAttribute("attendees", attendeeService.findAll());
        return "directory";
    }

    @RequestMapping(value = "/listOfTasks")
    public String listOfTasks(Model model) {
        model.addAttribute("title", "All Tasks");

        model.addAttribute("attendees", attendeeService.findAll());
        model.addAttribute("tasks", taskService.findAll());

        return "listOfTasks";

    }

    @GetMapping(value = "/detailsTask")
    public String detailsTask(Model model, @RequestParam int id) {
        Task task = taskService.findById(id);
        model.addAttribute("title", "Details");
        model.addAttribute("task", task);
        return "detailsTask";
    }

}
