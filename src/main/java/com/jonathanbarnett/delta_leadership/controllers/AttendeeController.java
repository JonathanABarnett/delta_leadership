package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.Attendee;
import com.jonathanbarnett.delta_leadership.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @GetMapping(value = "/addAttendee")
    public String addAttendee(Model model) {

        model.addAttribute("title", "Add Attendee");
        model.addAttribute("attendee", new Attendee());
        return "addAttendee";
    }

    @PostMapping(value = "/addAttendee")
    public String processAddAttendee(Model model, @ModelAttribute Attendee attendee, Principal principal) {
        model.addAttribute("added", true);
        String user = principal.getName();
        attendee.setAddedBy(user);
        attendeeService.save(attendee);
        model.addAttribute("attendee", attendee);
        model.addAttribute("attendees", attendeeService.findAll());
        return "directory";
    }

    @RequestMapping(value = "/detailsAttendee")
    public String detailsAttendee(Model model, @RequestParam int id) {
        Attendee attendee = attendeeService.findById(id);
        model.addAttribute("title", attendee.getLastName() + " Details");
        model.addAttribute("attendee", attendee);
        model.addAttribute("familyMembersList", attendee.getFamilyMembers());
        return "detailsAttendee";
    }

}
