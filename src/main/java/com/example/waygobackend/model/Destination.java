package com.example.waygobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    private String id;
    private String firebaseId;
    private String name;
    private String place;
    private String rating;
    private String price;
    private String match;
    private String reason;
    private String topLabel;
    private String imageUrl;
    private Instant syncedAt;
    private Boolean isDeleted;
}
