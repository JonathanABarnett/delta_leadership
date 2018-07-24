package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.Address;
import com.jonathanbarnett.delta_leadership.models.Leadership;
import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.security.PasswordResetToken;
import com.jonathanbarnett.delta_leadership.service.LeadershipService;
import com.jonathanbarnett.delta_leadership.service.UserSecurityService;
import com.jonathanbarnett.delta_leadership.service.UserService;
import com.jonathanbarnett.delta_leadership.utility.SecurityUtility;
import com.jonathanbarnett.delta_leadership.utility.USStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private LeadershipService leadershipService;


    @RequestMapping(value = "/profile")
    public String myProfile(Model model, Principal principal) {
        model.addAttribute("title", "My Profile");

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("classActiveEdit", true);

        List<Leadership> leadershipRoles = leadershipService.findAll();
        model.addAttribute("leadershipRoles", leadershipRoles);

        List<String> stateList = USStates.listOfUSStateCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        return "profile";
    }

    @GetMapping(value = "/editProfile")
    public String profile(Model model, @RequestParam("token") String token) {

        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);

        if (passwordResetToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/index";
        }

        User user = passwordResetToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("title", "Welcome " + user.getFirstName());
        model.addAttribute("user", user);

        if (user.getAddress() == null) {
            user.setAddress(new Address());
        }

        List<Leadership> leadershipRoles = leadershipService.findAll();
        model.addAttribute("leadershipRoles", leadershipRoles);

        List<String> stateList = USStates.listOfUSStateCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("classActiveEdit", true);

        return "profile";
    }

    @PostMapping(value = "/updateProfile")
    public String updateUserInfo(Model model, @ModelAttribute("user") User user,
                                 @ModelAttribute("newPassword") String newPassword) throws Exception {
        User currentUser = userService.findById(user.getId());
        System.out.println("User is: " + currentUser.getUsername());

        if (currentUser == null) {
            throw new Exception("User not found");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            if (userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
                model.addAttribute("emailExists", true);
                model.addAttribute("classActiveEdit", true);
                List<Leadership> leadershipRoles = new ArrayList<>();
                leadershipRoles = leadershipService.findAll();
                model.addAttribute("leadershipRoles", leadershipRoles);
                List<String> stateList = USStates.listOfUSStateCode;
                Collections.sort(stateList);
                model.addAttribute("stateList", stateList);
                return "profile";
            }
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            if (userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
                model.addAttribute("usernameExists", true);
                model.addAttribute("classActiveEdit", true);
                List<Leadership> leadershipRoles = new ArrayList<>();
                leadershipRoles = leadershipService.findAll();
                model.addAttribute("leadershipRoles", leadershipRoles);
                List<String> stateList = USStates.listOfUSStateCode;
                Collections.sort(stateList);
                model.addAttribute("stateList", stateList);
                return "profile";
            }
        }

        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = currentUser.getPassword();
            if (passwordEncoder.matches(user.getPassword(), dbPassword)) {
                currentUser.setPassword(passwordEncoder.encode(newPassword));
            } else {
                model.addAttribute("incorrectPassword", true);
                model.addAttribute("classActiveEdit", true);
                List<Leadership> leadershipRoles = new ArrayList<>();
                leadershipRoles = leadershipService.findAll();
                model.addAttribute("leadershipRoles", leadershipRoles);
                List<String> stateList = USStates.listOfUSStateCode;
                Collections.sort(stateList);
                model.addAttribute("stateList", stateList);
                return "profile";
            }
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        Address address = new Address();
        address.setStreet1(user.getAddress().getStreet1());
        address.setStreet2(user.getAddress().getStreet2());
        address.setCity(user.getAddress().getCity());
        address.setState(user.getAddress().getState());
        address.setZip(user.getAddress().getZip());
        currentUser.setLeadership(user.getLeadership());
        currentUser.setAddress(address);

        userService.save(currentUser);

        model.addAttribute("updateUserInfoSuccess", true);
        model.addAttribute("user", currentUser);
        model.addAttribute("classActiveEdit", true);
        List<Leadership> leadershipRoles = new ArrayList<>();
        leadershipRoles = leadershipService.findAll();
        model.addAttribute("leadershipRoles", leadershipRoles);
        List<String> stateList = USStates.listOfUSStateCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "profile";
    }
}
