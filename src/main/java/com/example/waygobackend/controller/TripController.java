package com.example.waygobackend.controller;

import com.example.waygobackend.model.ApiResponse;
import com.example.waygobackend.model.Trip;
import com.example.waygobackend.service.FirebaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "http://10.0.2.2")
public class TripController {

    private final FirebaseService firebaseService;

    public TripController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Trip>>> getTripsByUser(@RequestParam(required = false) String userId) {
        List<Trip> data;
        if (userId != null && !userId.isEmpty()) {
            data = firebaseService.getTripsByUser(userId);
        } else {
            data = List.of();
        }
        return ResponseEntity.ok(new ApiResponse<>(data, "Lấy danh sách trips thành công", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Trip>> createTrip(@RequestBody Trip trip) {
        String id = firebaseService.createTrip(trip);
        trip.setId(id);
        return ResponseEntity.ok(new ApiResponse<>(trip, "Tạo trip thành công", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Trip>> updateTrip(@PathVariable String id, @RequestBody Trip trip) {
        trip.setId(id);
        firebaseService.updateTrip(id, trip);
        return ResponseEntity.ok(new ApiResponse<>(trip, "Cập nhật trip thành công", true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrip(@PathVariable String id) {
        firebaseService.deleteTrip(id);
        return ResponseEntity.ok(new ApiResponse<>(null, "Xóa trip thành công", true));
    }
}
