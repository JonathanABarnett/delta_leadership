package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.utility.USStates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class ProfileController {

    @GetMapping(value = "/profile")
    public String profile(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("user", new User());

        List<String> stateList = USStates.listOfUSStateCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("classActiveEdit", true);

        return "profile";
    }
}
