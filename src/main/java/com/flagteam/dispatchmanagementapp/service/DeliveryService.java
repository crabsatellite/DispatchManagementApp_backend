package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.dto.DeliveryUploadDto;
import java.util.List;
import java.util.Map;

public interface DeliveryService {
    List<Map<String, Object>> getDeliveries(long userId, int limit, int offset, String sort);

    void uploadDelivery(DeliveryUploadDto dto, String username);
}
