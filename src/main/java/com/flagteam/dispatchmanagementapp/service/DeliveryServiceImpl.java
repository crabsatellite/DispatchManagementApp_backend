package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.dto.DeliveryDto;
import com.flagteam.dispatchmanagementapp.model.DeliveryInfo;
import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import com.flagteam.dispatchmanagementapp.model.Location;
import com.flagteam.dispatchmanagementapp.model.User;
import com.flagteam.dispatchmanagementapp.repository.DeliveryInfoRepository;
import com.flagteam.dispatchmanagementapp.repository.DeliveryItemRepository;
import com.flagteam.dispatchmanagementapp.repository.LocationRepository;
import com.flagteam.dispatchmanagementapp.repository.UserRepository;
import com.flagteam.dispatchmanagementapp.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryItemRepository deliveryItemRepository;
    private final DeliveryInfoRepository deliveryInfoRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public DeliveryServiceImpl(DeliveryItemRepository deliveryItemRepository, DeliveryInfoRepository deliveryInfoRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.deliveryItemRepository = deliveryItemRepository;
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<DeliveryDto> getDeliveries(UUID userId, int limit, int offset, String sort) {
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

        // Get all the delivery infos from the current user
        Page<DeliveryInfo> deliveryInfos = deliveryInfoRepository.findByUser(user.get(), pageable);

        // Create a list of DeliveryDto objects to hold the delivery data along with location data
        List<DeliveryDto> deliveryDtos = new ArrayList<>();

        for (DeliveryInfo deliveryInfo : deliveryInfos) {

            Page<DeliveryItem> deliveryPage = deliveryItemRepository.findByDeliveryInfo(deliveryInfo, pageable);
            List<DeliveryItem> deliveryItems = deliveryPage.getContent();

            DeliveryDto deliveryDto = new DeliveryDto();
            for(DeliveryItem item : deliveryItems) {

                // copy properties from DeliveryItem to DeliveryDto
                BeanUtils.copyProperties(item, deliveryDto);

                // get the location for the current delivery item
                Location location = locationRepository.findByDeliveryInfo(deliveryInfo);

                // set the location properties to the DeliveryDto
                deliveryDto.setCourierLastPositionLat(location.getLatitude());
                deliveryDto.setCourierLastPositionLng(location.getLongitude());
            }
            // add the DeliveryDto to the list
            deliveryDtos.add(deliveryDto);
        }
        return deliveryDtos;
    }
}
