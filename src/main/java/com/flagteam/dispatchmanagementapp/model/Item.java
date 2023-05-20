package com.flagteam.dispatchmanagementapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "item_table")
public class Item implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long id;
    private String name;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_item_id")
    @JsonIgnore
    private DeliveryItem deliveryItem;

    public long getId() {
        return id;
    }

    public Item setId(long id) {
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

    public int getQuantity() {
        return quantity;
    }

    public Item setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
