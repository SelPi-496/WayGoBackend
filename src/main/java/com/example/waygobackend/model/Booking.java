package com.example.waygobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String id;
    private String firebaseId;
    private String tripId;
    private String service;
    private String price;
    private String status;
    private Instant syncedAt;
    private Boolean isDeleted;
    private Instant createdAt;
}
