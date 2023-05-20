package com.flagteam.dispatchmanagementapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "delivery_item_table")
public class DeliveryItem implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_item_id")
    private long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_info_id")
    private DeliveryInfo deliveryInfo;
    @OneToMany(mappedBy = "deliveryItem", cascade=CascadeType.ALL)
    private Set<Item> items;

    public long getId() {
        return id;
    }

    public DeliveryItem setId(long id) {
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

}
