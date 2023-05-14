package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.dto.DeliveryDto;
import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    List<DeliveryDto> getDeliveries(UUID userId, int limit, int offset, String sort);
}
