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
import java.security.Principal;

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
    public String processAddAttendee(Model model, @ModelAttribute FamilyMember familyMember, Principal principal) {
        model.addAttribute("added", true);
        String user = principal.getName();
        familyMember.setAddedBy(user);
        Attendee attendee = familyMember.getAttendee();
        System.out.println(attendee.getLastName() + " " + attendee.getId());
        familyMemberService.save(familyMember);
        attendee.addFamilyMember(familyMember);

        model.addAttribute("attendees", attendeeService.findAll());
        return "redirect:/detailsAttendee?id=" + attendee.getId();
    }

    @GetMapping(value = "/detailsFamilyMember")
    public String familyMemberDetails(Model model, @RequestParam("id") int id) {
        model.addAttribute("title", "View Info");
        FamilyMember familyMember = familyMemberService.findById(id);
        model.addAttribute("familyMember", familyMember);
        Attendee attendee = familyMember.getAttendee();
        model.addAttribute("attendee", attendee);

        FamilyMember spouse = null;
        if (attendee.getFamilyMembers().size() > 1) {
            for (FamilyMember fm : attendee.getFamilyMembers()) {
                if (fm.getId() != familyMember.getId()) {
                    spouse = fm;
                }
            }
            model.addAttribute("spouse", spouse);
        }



        return "detailsFamilyMember";
    }

    @GetMapping(value = "/updateFamilyMember")
    public String updateFamilyMember(Model model, @RequestParam("id") int id) {
        FamilyMember familyMember = familyMemberService.findById(id);
        Attendee attendee = familyMember.getAttendee();
        model.addAttribute("title", "Update " + familyMember.getFirstName());
        model.addAttribute("familyMember", familyMember);
        model.addAttribute("attendee", attendee);
        return "updateFamilyMember";
    }

    @PostMapping(value = "/updateFamilyMember")
    public String processUpdateFamilyMember(@ModelAttribute("familyMember") FamilyMember familyMember, Model model, Principal principal) {
        model.addAttribute("added", true);
        String user = principal.getName();
        familyMember.setAddedBy(user);
        Attendee attendee = familyMember.getAttendee();
        System.out.println(attendee.getLastName() + " " + attendee.getId());
        familyMemberService.save(familyMember);
        model.addAttribute("attendees", attendeeService.findAll());
        return "redirect:/detailsAttendee?id=" + attendee.getId();
    }
}
