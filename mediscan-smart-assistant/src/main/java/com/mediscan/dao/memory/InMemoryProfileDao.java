package com.mediscan.dao.memory;

import com.mediscan.dao.ProfileDao;
import com.mediscan.model.Profile;

import java.util.Optional;
import java.util.UUID;

public class InMemoryProfileDao implements ProfileDao {
    @Override
    public Profile upsert(Profile profile) {
        InMemoryStores.profiles.put(profile.getProfileId(), profile);
        return profile;
    }

    @Override
    public Optional<Profile> findByUserId(UUID userId) {
        return InMemoryStores.profiles.values().stream()
                .filter(p -> p.getUserId().equals(userId))
                .findFirst();
    }
}
