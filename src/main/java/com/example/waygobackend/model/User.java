package com.example.waygobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String firebaseUid;
    private String email;
    private String displayName;
    private String photoUrl;
    private Instant syncedAt;
    private Instant createdAt;
}
