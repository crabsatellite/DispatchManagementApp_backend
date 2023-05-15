package com.flagteam.dispatchmanagementapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "delivery_item")
public class DeliveryItem implements Serializable {
    private static final long versionId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int quantity;
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryInfo deliveryInfo;
    @OneToMany
    private Set<Item> items;

    public UUID getId() {
        return id;
    }

    public DeliveryItem setId(UUID id) {
        this.id = id;
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
