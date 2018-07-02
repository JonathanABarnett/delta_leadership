package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.security.PasswordResetToken;
import com.jonathanbarnett.delta_leadership.security.UserRole;

import java.util.Set;

public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findByEmail (String email);

    User createUser(User user, Set<UserRole>userRoles);
}
