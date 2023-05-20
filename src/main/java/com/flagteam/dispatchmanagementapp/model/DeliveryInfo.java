package com.flagteam.dispatchmanagementapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "delivery_info_table")
public class DeliveryInfo implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="delivery_info_id")
    private long id;
    @Column(name="delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "warehouse_id")
    private int warehouseId;
    @Column(name = "courier_id")
    private int courierId;
    @Column(name="sender_name")
    private String senderName;
    @Column(name="receiver_name")
    private String receiverName;
    @Column(name="receiver_email")
    private String receiverEmail;
    @Column(name="receiver_phone_number")
    private String receiverPhoneNumber;
    @Column(name="sender_address")
    private String senderAddress;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "pickup_speed")
    private String pickUpSpeed;
    @Column(name = "delivery_speed")
    private String deliverySpeed;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private DeliveryStatus deliveryStatus;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    @OneToMany(mappedBy = "deliveryInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<DeliveryItem> deliveryItem;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public DeliveryInfo setUser(User user) {
        this.user = user;
        return this;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public DeliveryInfo setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public Set<DeliveryItem> getDeliveryItem() {
        return deliveryItem;
    }

    public DeliveryInfo setDeliveryItem(Set<DeliveryItem> deliveryItem) {
        this.deliveryItem = deliveryItem;
        return this;
    }

    public long getId() {
        return id;
    }

    public DeliveryInfo setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public DeliveryInfo setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public DeliveryInfo setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public int getCourierId() {
        return courierId;
    }

    public DeliveryInfo setCourierId(int courierId) {
        this.courierId = courierId;
        return this;
    }

    public String getSenderName() {
        return senderName;
    }

    public DeliveryInfo setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public DeliveryInfo setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public DeliveryInfo setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
        return this;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public DeliveryInfo setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
        return this;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public DeliveryInfo setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public DeliveryInfo setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public String getPickUpSpeed() {
        return pickUpSpeed;
    }

    public DeliveryInfo setPickUpSpeed(String pickUpSpeed) {
        this.pickUpSpeed = pickUpSpeed;
        return this;
    }

    public String getDeliverySpeed() {
        return deliverySpeed;
    }

    public DeliveryInfo setDeliverySpeed(String deliverySpeed) {
        this.deliverySpeed = deliverySpeed;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public DeliveryInfo setLocation(Location location) {
        this.location = location;
        return this;
    }
}
