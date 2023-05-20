package com.flagteam.dispatchmanagementapp.repository;

import com.flagteam.dispatchmanagementapp.model.DeliveryInfo;
import com.flagteam.dispatchmanagementapp.model.Location;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    Location findByDeliveryInfo(DeliveryInfo deliveryInfo);
}
