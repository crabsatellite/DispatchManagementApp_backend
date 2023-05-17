package com.flagteam.dispatchmanagementapp.repository;

import com.flagteam.dispatchmanagementapp.model.DeliveryInfo;
import com.flagteam.dispatchmanagementapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Long> {
    Page<DeliveryInfo> findByUser(User user, Pageable pageable);

    @Query(value = "SELECT u FROM DeliveryInfo u WHERE u.user.id =?1")
    List<DeliveryInfo> findByUserId(long userId);
}
