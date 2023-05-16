
package com.flagteam.dispatchmanagementapp.dto;

import com.flagteam.dispatchmanagementapp.model.DeliveryInfo;
import com.flagteam.dispatchmanagementapp.model.DeliveryItem;
import com.flagteam.dispatchmanagementapp.model.DeliveryStatus;
import com.flagteam.dispatchmanagementapp.model.Location;

public class DeliveryUploadDto {

    private DeliveryInfo deliveryInfo;
    private DeliveryStatus deliveryStatus;
    private DeliveryItem deliveryItem;
    private Location location;

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public DeliveryItem getDeliveryItem() {
        return deliveryItem;
    }

    public Location getLocation() {
        return location;
    }
}
