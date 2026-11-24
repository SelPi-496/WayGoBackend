package com.example.waygobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private String id;
    private String firebaseId;
    private String destinationId;
    private String range;
    private Integer days;
    private String status;
    private String userId;
    private Instant syncedAt;
    private Boolean isDeleted;
    private Instant createdAt;
}
