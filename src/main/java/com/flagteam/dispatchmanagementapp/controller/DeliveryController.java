package com.flagteam.dispatchmanagementapp.controller;

import com.flagteam.dispatchmanagementapp.dto.ApiErrorResponse;
import com.flagteam.dispatchmanagementapp.dto.ApiResponse;
import com.flagteam.dispatchmanagementapp.dto.DeliveryDto;
import com.flagteam.dispatchmanagementapp.dto.DeliveryUploadDto;
import com.flagteam.dispatchmanagementapp.model.DeliveryInfo;
import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import com.flagteam.dispatchmanagementapp.model.DeliveryStatus;
import com.flagteam.dispatchmanagementapp.model.Location;
import com.flagteam.dispatchmanagementapp.service.DeliveryService;
import com.flagteam.dispatchmanagementapp.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;


    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<?> getDeliveries(
            @RequestParam(value = "userId")UUID userId,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "sort", defaultValue = "deliveryDate:desc") String sort) {
        try {
            List<DeliveryDto> deliveries = deliveryService.getDeliveries(userId, limit, offset, sort);
            ApiResponse<List<DeliveryDto>> response = new ApiResponse<>(200, deliveries);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            ApiErrorResponse error = new ApiErrorResponse("User not found", "USER_NOT_FOUND");
            ApiResponse<ApiErrorResponse> response = new ApiResponse<>(404, error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> upLoadDelivery(@RequestBody DeliveryUploadDto dto,
                                            Principal principal) {

        try {
            deliveryService.uploadDelivery(dto, principal.getName());
            ApiResponse<String> response = new ApiResponse<>(200, "SUCCESS");
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            ApiErrorResponse error = new ApiErrorResponse("User not found", "USER_NOT_FOUND");
            ApiResponse<ApiErrorResponse> response = new ApiResponse<>(404, error);
            return ResponseEntity.status(404).body(response);
        }
    }
}
