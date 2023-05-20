package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.dto.*;
import com.flagteam.dispatchmanagementapp.exception.UserNotFoundException;
import com.flagteam.dispatchmanagementapp.model.*;
import com.flagteam.dispatchmanagementapp.repository.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

  private final DeliveryItemRepository deliveryItemRepository;
  private final DeliveryInfoRepository deliveryInfoRepository;
  private final UserRepository userRepository;
  private final LocationRepository locationRepository;

  public DeliveryServiceImpl(
      DeliveryItemRepository deliveryItemRepository,
      DeliveryInfoRepository deliveryInfoRepository,
      UserRepository userRepository,
      LocationRepository locationRepository) {
    this.deliveryItemRepository = deliveryItemRepository;
    this.deliveryInfoRepository = deliveryInfoRepository;
    this.userRepository = userRepository;
    this.locationRepository = locationRepository;
  }

  @Override
  public List<Map<String, Object>> getDeliveries(long userId, int limit, int offset, String sort) {
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

    Sort.Direction sortDirection =
        order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    Pageable pageable = PageRequest.of(offset, limit, Sort.by(sortDirection, field));

    Page<DeliveryInfo> deliveryInfosPage = deliveryInfoRepository.findByUser(user.get(), pageable);

    List<Map<String, Object>> orderHistoryCollection = new ArrayList<>();
    List<DeliveryInfo> deliveryInfos = deliveryInfosPage.getContent();

    for (DeliveryInfo deliveryInfo : deliveryInfos) {
      Map<String, Object> orderHistory = new HashMap<>();

      // Set DeliveryInfo
      orderHistory.put("deliveryInfo", deliveryInfo);

      // Set DeliveryStatus
      DeliveryStatus deliveryStatus = deliveryInfo.getDeliveryStatus();
      orderHistory.put("deliveryStatus", deliveryStatus);

      // Set DeliveryItem
      List<DeliveryItem> deliveryItems = deliveryItemRepository.findByDeliveryInfo(deliveryInfo);
      orderHistory.put("deliveryItem", deliveryItems);

      orderHistoryCollection.add(orderHistory);
    }

    return orderHistoryCollection;


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

    Set<DeliveryItem> deliveryItems = new HashSet<>();
    deliveryItems.add(deliveryItem);
    for (Item item : deliveryItem.getItems()) {
      item.setDeliveryItem(deliveryItem);
    }
    deliveryItem.setDeliveryInfo(deliveryInfo);
    deliveryStatus.setDeliveryInfo(deliveryInfo);
    location.setDeliveryInfo(deliveryInfo);
    deliveryInfo.setUser(user.get(0));
    deliveryInfo.setDeliveryStatus(deliveryStatus);
    deliveryInfo.setDeliveryItem(deliveryItems);
    deliveryInfo.setLocation(location);
    deliveryInfoRepository.save(deliveryInfo);
  }
}
