package com.jonathanbarnett.delta_leadership;

import com.jonathanbarnett.delta_leadership.models.Address;
import com.jonathanbarnett.delta_leadership.models.Leadership;
import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.security.Role;
import com.jonathanbarnett.delta_leadership.security.UserRole;
import com.jonathanbarnett.delta_leadership.service.UserService;
import com.jonathanbarnett.delta_leadership.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DeltaLeadershipApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DeltaLeadershipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("Admin");
        user1.setLastName("Admin");
        user1.setEmail("jb@gmail.com");
        user1.setUsername("Admin");
        user1.setPhoneNumber("123-456-7899");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("123"));
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user1, role1));
        Address address1 = new Address();
        address1.setStreet1("123 S. St.");
        address1.setStreet2("");
        address1.setCity("Springfield");
        address1.setState("IL");
        address1.setZip("62704");
        user1.setAddress(address1);

        userService.createUser(user1, userRoles);
    }
}
