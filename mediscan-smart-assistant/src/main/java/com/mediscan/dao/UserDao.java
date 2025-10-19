package com.mediscan.dao;

import com.mediscan.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    User upsert(User user);
    Optional<User> findById(UUID userId);
    Optional<User> findByEmail(String email);
    List<User> listAll();
}
