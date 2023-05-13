package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import com.flagteam.dispatchmanagementapp.model.User;
import com.flagteam.dispatchmanagementapp.repository.DeliveryRepository;
import com.flagteam.dispatchmanagementapp.repository.UserRepository;
import com.flagteam.dispatchmanagementapp.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, UserRepository userRepository) {
        this.deliveryRepository = deliveryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DeliveryItem> getDeliveries(UUID userId, int limit, int offset, String sort) {
        // Check if the user exists
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        // Validate limit and offset
        if (limit <= 0 || limit > 100) {
            throw new IllegalArgumentException("Limit should be between 1 and 100");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset should be greater than or equal to 0");
        }

        // Validate sort parameter
        String[] sortParams = sort.split(":");
        if (sortParams.length != 2) {
            throw new IllegalArgumentException("Sort parameter should be in the format 'field:order'");
        }
        String field = sortParams[0];
        String order = sortParams[1];
        if (!field.equals("deliveryDate")) {
            throw new IllegalArgumentException("Invalid field in sort parameter: " + field);
        }
        if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Invalid order in sort parameter: " + order);
        }

        Sort.Direction sortDirection = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(sortDirection, field));

        Page<DeliveryItem> deliveryPage = deliveryRepository.findByUserId(userId, pageable);
        return deliveryPage.getContent();
    }
}
