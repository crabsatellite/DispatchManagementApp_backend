package com.flagteam.dispatchmanagementapp.repository;

import com.flagteam.dispatchmanagementapp.model.DeliveryInfo;
import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long> {
    Page<DeliveryItem> findByDeliveryInfo(DeliveryInfo deliveryInfo, Pageable pageable);
}

