package com.example.waygobackend.controller;

import com.example.waygobackend.model.ApiResponse;
import com.example.waygobackend.model.Booking;
import com.example.waygobackend.service.FirebaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://10.0.2.2")
public class BookingController {

    private final FirebaseService firebaseService;

    public BookingController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingsByTrip(@RequestParam(required = false) String tripId) {
        List<Booking> data;
        if (tripId != null && !tripId.isEmpty()) {
            data = firebaseService.getBookingsByTrip(tripId);
        } else {
            data = List.of();
        }
        return ResponseEntity.ok(new ApiResponse<>(data, "Lấy danh sách bookings thành công", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody Booking booking) {
        String id = firebaseService.createBooking(booking);
        booking.setId(id);
        return ResponseEntity.ok(new ApiResponse<>(booking, "Tạo booking thành công", true));
    }
}
