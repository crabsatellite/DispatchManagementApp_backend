package com.flagteam.dispatchmanagementapp.repository;

import com.flagteam.dispatchmanagementapp.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Add any custom queries if needed

    List<User> findByUsername(String username);

    Optional<User> findById(long userId);
}
