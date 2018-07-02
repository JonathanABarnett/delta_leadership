package com.jonathanbarnett.delta_leadership.repository;

import com.jonathanbarnett.delta_leadership.models.User;
import com.jonathanbarnett.delta_leadership.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
    Stream<PasswordResetToken> findAllByExpirationDateLessThan(Date now);

    @Modifying
    @Query("DELETE FROM PasswordResetToken token WHERE token.expirationDate <= ?1")
    void deleteAllExpiredSince(Date now);

}
