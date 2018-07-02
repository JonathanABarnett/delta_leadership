package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.repository.PasswordResetTokenRepository;
import com.jonathanbarnett.delta_leadership.repository.RoleRepository;
import com.jonathanbarnett.delta_leadership.repository.UserRepository;
import com.jonathanbarnett.delta_leadership.security.PasswordResetToken;
import com.jonathanbarnett.delta_leadership.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        final PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null) {
            System.out.println("User already exists");
        } else {
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
