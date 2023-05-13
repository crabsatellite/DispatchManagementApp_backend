package com.flagteam.dispatchmanagementapp.repository;

import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<DeliveryItem, Long> {
    Page<DeliveryItem> findByUserId(UUID userId, Pageable pageable);
}

