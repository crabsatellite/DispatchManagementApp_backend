package com.flagteam.dispatchmanagementapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_table")
public class User implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private LocalDate createdDate;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<DeliveryInfo> deliveryInfos;

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public User setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<DeliveryInfo> getDeliveryInfos() {
        return deliveryInfos;
    }

    public User setDeliveryInfos(Set<DeliveryInfo> deliveryInfos) {
        this.deliveryInfos = deliveryInfos;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }
}
