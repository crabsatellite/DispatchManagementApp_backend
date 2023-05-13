package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    List<DeliveryItem> getDeliveries(UUID userId, int limit, int offset, String sort);
}
