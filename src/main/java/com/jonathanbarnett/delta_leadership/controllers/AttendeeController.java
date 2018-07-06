package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.Attendee;
import com.jonathanbarnett.delta_leadership.models.FamilyMember;
import com.jonathanbarnett.delta_leadership.models.FamilyMemberCreation;
import com.jonathanbarnett.delta_leadership.repository.FamilyMemberRepository;
import com.jonathanbarnett.delta_leadership.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String processAddAttendee(Model model, @ModelAttribute Attendee attendee) {
        model.addAttribute("added", true);
        attendeeService.save(attendee);
        model.addAttribute("attendee", attendee);
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
