package com.jonathanbarnett.delta_leadership.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectoryController {

    @GetMapping(value = "/directory")
    public String listDirectory(Model model) {
        model.addAttribute("title", "Directory");
        return "directory";
    }
}
