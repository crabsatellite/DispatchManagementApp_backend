package com.flagteam.dispatchmanagementapp.repository;

import com.flagteam.dispatchmanagementapp.model.DeliveryInfo;
import com.flagteam.dispatchmanagementapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Long> {
    Page<DeliveryInfo> findByUser(User user, Pageable pageable);
}
