package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.dto.DeliveryDto;
import com.flagteam.dispatchmanagementapp.dto.DeliveryUploadDto;
import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    List<DeliveryDto> getDeliveries(UUID userId, int limit, int offset, String sort);

    void uploadDelivery(DeliveryUploadDto dto, String username);
}
