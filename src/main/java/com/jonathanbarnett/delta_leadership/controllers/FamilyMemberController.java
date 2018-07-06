package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.Attendee;
import com.jonathanbarnett.delta_leadership.models.FamilyMember;
import com.jonathanbarnett.delta_leadership.service.AttendeeService;
import com.jonathanbarnett.delta_leadership.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@Controller
public class FamilyMemberController {

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private FamilyMemberService familyMemberService;

    @GetMapping(value = "/addFamilyMember")
    public String addAttendee(Model model, @RequestParam("id") int id) {
        Attendee attendee = attendeeService.findById(id);
        model.addAttribute("attendee", attendee);
        System.out.println(attendee.getLastName() + " " + attendee.getId());
        model.addAttribute("title", "Add Attendee");
        model.addAttribute("familyMember", new FamilyMember());
        return "addFamilyMember";
    }

    @PostMapping(value = "/addFamilyMember")
    public String processAddAttendee(Model model, @ModelAttribute FamilyMember familyMember) {
        model.addAttribute("added", true);
        Attendee attendee = familyMember.getAttendee();
        System.out.println(attendee.getLastName() + " " + attendee.getId());
        familyMemberService.save(familyMember);
        attendee.addFamilyMember(familyMember);

        model.addAttribute("attendees", attendeeService.findAll());
        return "redirect:/detailsAttendee?id=" + attendee.getId();
    }

//    @GetMapping(value = "/addFamilyMember")
//    public String addFamilyMember(Model model, @RequestParam("id") int id) {
//        model.addAttribute("title", "Add Family Member");
//        Attendee attendee = attendeeService.findById(id);
//        model.addAttribute("attendee", attendee);
//
//        return "addFamilyMember";
//    }
//
//    @PostMapping(value = "/addFamilyMember")
//    public String processAddFamilyMember(Model model, @ModelAttribute("attendee") Attendee attendee,
//                                         @ModelAttribute("familyMember") FamilyMember familyMember) {
//        model.addAttribute("added", true);
//        attendee.addFamilyMember(familyMember);
//        attendeeService.save(attendee);
//        familyMemberService.saveAll(attendee.getFamilyMembers());
//        model.addAttribute("attendees", attendeeService.findAll());
//        return "directory";
//    }
}