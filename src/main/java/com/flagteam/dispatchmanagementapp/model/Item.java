package com.flagteam.dispatchmanagementapp.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "item_table")
public class Item implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name="delivery_item_id")
    private DeliveryItem deliveryItem;

    public UUID getId() {
        return id;
    }

    public Item setId(UUID id) {
        this.id = id;
        return this;
    }

    public DeliveryItem getDeliveryItem() {
        return deliveryItem;
    }

    public Item setDeliveryItem(DeliveryItem deliveryItem) {
        this.deliveryItem = deliveryItem;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }
}
