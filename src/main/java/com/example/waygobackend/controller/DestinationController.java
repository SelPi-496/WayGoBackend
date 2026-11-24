package com.example.waygobackend.controller;

import com.example.waygobackend.model.ApiResponse;
import com.example.waygobackend.model.Destination;
import com.example.waygobackend.service.FirebaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin(origins = "http://10.0.2.2")
public class DestinationController {

    private final FirebaseService firebaseService;

    public DestinationController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Destination>>> getAll() {
        List<Destination> data = firebaseService.getAllDestinations();
        return ResponseEntity.ok(new ApiResponse<>(data, "Lấy danh sách destinations thành công", true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> getById(@PathVariable String id) {
        Destination data = firebaseService.getDestinationById(id);
        if (data == null) {
            return ResponseEntity.ok(new ApiResponse<>(null, "Không tìm thấy destination", false));
        }
        return ResponseEntity.ok(new ApiResponse<>(data, "Lấy destination thành công", true));
    }
}
