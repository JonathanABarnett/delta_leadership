package com.jonathanbarnett.delta_leadership.controllers;

import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.security.PasswordResetToken;
import com.jonathanbarnett.delta_leadership.security.Role;
import com.jonathanbarnett.delta_leadership.security.UserRole;
import com.jonathanbarnett.delta_leadership.service.UserSecurityService;
import com.jonathanbarnett.delta_leadership.service.UserService;
import com.jonathanbarnett.delta_leadership.utility.MailConstructor;
import com.jonathanbarnett.delta_leadership.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailConstructor mailConstructor;


    @GetMapping(value = "/login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("classActiveLogin", true);
        return "login";
    }

    @GetMapping(value = "/createAccount")
    public String createAccount(Model model, Locale locale, @RequestParam("token") String token) {

        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);

        if (passwordResetToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/access-denied";
        }

        User user = passwordResetToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("title", "Create Account");
        model.addAttribute("user", user);
        model.addAttribute("classActiveCreate", true);
        return "login";
    }

    @PostMapping(value = "/createAccount")
    public String processCreateAccount(Model model, HttpServletRequest request, @ModelAttribute("email") String email,
                                @ModelAttribute("username") String username) throws Exception {
        model.addAttribute("classActiveCreate", true);
        model.addAttribute("email", email);
        model.addAttribute("username", username);

        if (userService.findByUsername(username) != null) {
            System.out.println("Username exists");
            model.addAttribute("usernameExists", true);
            model.addAttribute("classActiveCreate", true);
            return "login";
        }

        if (userService.findByEmail(email) != null) {
            System.out.println("Email Exists");
            model.addAttribute("emailExists", true);
            model.addAttribute("classActiveCreate", true);
            return "login";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);

        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(2);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        SimpleMailMessage simpleMailMessage = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        javaMailSender.send(simpleMailMessage);

        model.addAttribute("emailSent", true);
        model.addAttribute("classActiveRegister", true);

        return "login";
    }

    @GetMapping(value = "/forgotPassword")
    public String forgotPassword(Model model) {

        model.addAttribute("title", "Forgot Password");
        model.addAttribute("classActiveForgot", true);
        return "login";
    }
}
