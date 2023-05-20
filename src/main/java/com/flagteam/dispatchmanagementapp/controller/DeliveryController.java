package com.flagteam.dispatchmanagementapp.controller;
import com.flagteam.dispatchmanagementapp.dto.ApiErrorResponse;
import com.flagteam.dispatchmanagementapp.dto.ApiResponse;
import com.flagteam.dispatchmanagementapp.dto.DeliveryUploadDto;
import com.flagteam.dispatchmanagementapp.exception.UserNotFoundException;
import com.flagteam.dispatchmanagementapp.model.User;
import com.flagteam.dispatchmanagementapp.repository.UserRepository;
import com.flagteam.dispatchmanagementapp.service.DeliveryService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final UserRepository userRepository;

    public DeliveryController(DeliveryService deliveryService, UserRepository userRepository) {
        this.deliveryService = deliveryService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getDeliveries(
            Principal principal,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "sort", defaultValue = "deliveryDate:desc") String sort) {

        try {
            String userName = principal.getName();
            List<User> users = userRepository.findByUsername(userName);
            if (users.isEmpty()) {
                throw new UserNotFoundException("User not found with username: " + userName);
            }
            long userId = users.get(0).getId();
            List<Map<String, Object>> deliveries = deliveryService.getDeliveries(userId, limit, offset, sort);
            ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(200, deliveries);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                ApiErrorResponse error = new ApiErrorResponse(e.getMessage(), "INVALID_PARAMETER");
                ApiResponse<ApiErrorResponse> response = new ApiResponse<>(400, error);
                return ResponseEntity.status(400).body(response);
            } else if (e instanceof UserNotFoundException) {
                ApiErrorResponse error = new ApiErrorResponse(e.getMessage(), "USER_NOT_FOUND");
                ApiResponse<ApiErrorResponse> response = new ApiResponse<>(404, error);
                return ResponseEntity.status(404).body(response);
            } else {
                ApiErrorResponse error = new ApiErrorResponse(e.getMessage(), "INTERNAL_SERVER_ERROR");
                ApiResponse<ApiErrorResponse> response = new ApiResponse<>(500, error);
                return ResponseEntity.status(500).body(response);
            }
        }
    }

    @PostMapping
    public ResponseEntity<String> upLoadDelivery(@RequestBody DeliveryUploadDto dto,
                                            Principal principal) {

        deliveryService.uploadDelivery(dto, principal.getName());
        return new ResponseEntity<String>("Upload delivery is successfully",HttpStatus.OK);
    }
}
