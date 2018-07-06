package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectoryController {

    @Autowired
    private AttendeeService attendeeService;

    @GetMapping(value = "/directory")
    public String listDirectory(Model model) {
        model.addAttribute("title", "Directory");
        model.addAttribute("attendees", attendeeService.findAll());
        return "directory";
    }
}
