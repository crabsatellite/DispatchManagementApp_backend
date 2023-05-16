package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.dto.DeliveryDto;
import com.flagteam.dispatchmanagementapp.dto.DeliveryUploadDto;
import com.flagteam.dispatchmanagementapp.model.*;
import com.flagteam.dispatchmanagementapp.repository.*;
import com.flagteam.dispatchmanagementapp.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryItemRepository deliveryItemRepository;
    private final DeliveryInfoRepository deliveryInfoRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public DeliveryServiceImpl(DeliveryItemRepository deliveryItemRepository,
                               DeliveryInfoRepository deliveryInfoRepository,
                               DeliveryStatusRepository deliveryStatusRepository,
                               ItemRepository itemRepository,
                               UserRepository userRepository,
                               LocationRepository locationRepository) {
        this.deliveryItemRepository = deliveryItemRepository;
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.deliveryStatusRepository = deliveryStatusRepository;
        this.itemRepository = itemRepository;
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

    @Override
    public void uploadDelivery(DeliveryUploadDto dto, String username) {

        List<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        // Read data
        DeliveryStatus deliveryStatus = dto.getDeliveryStatus();
        DeliveryInfo deliveryInfo = dto.getDeliveryInfo();
        DeliveryItem deliveryItem = dto.getDeliveryItem();
        Location location = dto.getLocation();

        // Save items
        Set<Item> items = deliveryItem.getItems();
        for (Item item : items) {
            item.setDeliveryItem(deliveryItem);
            itemRepository.save(item);
        }

        // Save delivery item
        Set<DeliveryItem> deliveryItems = new HashSet<>();
        deliveryItems.add(deliveryItem);
        deliveryItemRepository.save(deliveryItem);

        // Save delivery info
        deliveryInfo.setUser(user.get(0));
        deliveryInfo.setDeliveryStatus(deliveryStatus);
        deliveryInfo.setDeliveryItem(deliveryItems);
        deliveryInfoRepository.save(deliveryInfo);

        // Save location
        location.setDeliveryInfo(deliveryInfo);
        location.setCreatedDate(LocalDate.now());
        location.setUpdatedDate(LocalDate.now());
        location.setType("COURIER_LAST_LOCATION");
        locationRepository.save(location);

        // Save delivery status
        deliveryStatusRepository.save(deliveryStatus);
    }
}
