package com.flagteam.dispatchmanagementapp.model;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "delivery_info_table")
public class DeliveryInfo implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="delivery_info_id")
    private UUID id;
    @Column(name="delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "warehouse_id")
    private int warehouseId;
    @Column(name = "is_robot")
    private boolean isRobot;
    private String sender;
    private String receiver;
    @Column(name="sender_address")
    private String senderAddress;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "pick_up_speed")
    private String pickUpSpeed;
    @Column(name = "delivery_speed")
    private String deliverySpeed;
    @OneToOne
    @JoinColumn(name = "status_id")
    private DeliveryStatus deliveryStatus;
    @OneToMany(mappedBy = "deliveryInfo")
    private Set<DeliveryItem> deliveryItem;
    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public UUID getId() {
        return id;
    }

    public DeliveryInfo setId(UUID id) {
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

    public boolean isRobot() {
        return isRobot;
    }

    public DeliveryInfo setRobot(boolean robot) {
        isRobot = robot;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public DeliveryInfo setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public DeliveryInfo setReceiver(String receiver) {
        this.receiver = receiver;
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
}
