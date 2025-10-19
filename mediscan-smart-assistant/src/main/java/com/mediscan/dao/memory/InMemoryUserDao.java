package com.mediscan.dao.memory;

import com.mediscan.dao.UserDao;
import com.mediscan.model.User;

import java.util.*;

public class InMemoryUserDao implements UserDao {
    @Override
    public User upsert(User user) {
        InMemoryStores.users.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return Optional.ofNullable(InMemoryStores.users.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return InMemoryStores.users.values().stream()
                .filter(u -> Objects.equals(u.getEmail(), email))
                .findFirst();
    }

    @Override
    public List<User> listAll() {
        return new ArrayList<>(InMemoryStores.users.values());
    }
}
