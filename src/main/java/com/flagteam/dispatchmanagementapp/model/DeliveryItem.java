package com.flagteam.dispatchmanagementapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "delivery_item_table")
public class DeliveryItem implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "delivery_item_id")
    private UUID id;
    private int quantity;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "delivery_info_id")
    private DeliveryInfo deliveryInfo;
    @OneToMany(mappedBy = "deliveryItem")
    private Set<Item> items;

    public UUID getId() {
        return id;
    }

    public DeliveryItem setId(UUID id) {
        this.id = id;
        return this;
    }

    public Set<Item> getItems() {
        return items;
    }

    public DeliveryItem setItems(Set<Item> items) {
        this.items = items;
        return this;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public DeliveryItem setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public DeliveryItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
