package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.Attendee;
import com.jonathanbarnett.delta_leadership.models.FamilyMember;
import com.jonathanbarnett.delta_leadership.models.FamilyMemberCreation;
import com.jonathanbarnett.delta_leadership.repository.FamilyMemberRepository;
import com.jonathanbarnett.delta_leadership.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @GetMapping(value = "/addAttendee")
    public String addAttendee(Model model) {
        model.addAttribute("title", "Add Attendee");
        Attendee attendee = new Attendee();
        model.addAttribute("attendee", attendee);

        for (int i = 1; i <= 2; i++) {
            attendee.addFamilyMember(new FamilyMember());
        }
        return "addAttendee";
    }

    @PostMapping(value = "/addAttendee")
    public String processAddAttendee(Model model, @ModelAttribute Attendee attendee) {
        model.addAttribute("added", true);
        attendeeService.save(attendee);
        familyMemberRepository.saveAll(attendee.getFamilyMembers());
        model.addAttribute("attendees", attendeeService.findAll());
        return "directory";
    }
}
