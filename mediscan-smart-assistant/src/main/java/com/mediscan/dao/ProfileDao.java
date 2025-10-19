package com.mediscan.dao;

import com.mediscan.model.Profile;

import java.util.Optional;
import java.util.UUID;

public interface ProfileDao {
    Profile upsert(Profile profile);
    Optional<Profile> findByUserId(UUID userId);
}
