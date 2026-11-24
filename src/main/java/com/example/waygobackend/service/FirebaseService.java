package com.example.waygobackend.service;

import com.example.waygobackend.model.ApiResponse;
import com.example.waygobackend.model.Booking;
import com.example.waygobackend.model.Destination;
import com.example.waygobackend.model.Trip;
import com.example.waygobackend.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class FirebaseService {

    private final Firestore db;

    public FirebaseService(Firestore db) {
        this.db = db;
    }

    private static final String COLLECTION_USERS = "users";
    private static final String COLLECTION_TRIPS = "trips";
    private static final String COLLECTION_BOOKINGS = "bookings";
    private static final String COLLECTION_DESTINATIONS = "destinations";

    // ========== Destinations ==========

    public List<Destination> getAllDestinations() {
        try {
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_DESTINATIONS).get();
            QuerySnapshot snapshot = future.get();
            List<Destination> list = new ArrayList<>();
            for (DocumentSnapshot doc : snapshot.getDocuments()) {
                Destination d = doc.toObject(Destination.class);
                if (d != null && d.getId() == null) {
                    d.setId(doc.getId());
                }
                list.add(d);
            }
            return list;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi lấy destinations: " + e.getMessage(), e);
        }
    }

    public Destination getDestinationById(String id) {
        try {
            DocumentSnapshot doc = db.collection(COLLECTION_DESTINATIONS).document(id).get().get();
            if (doc.exists()) {
                Destination d = doc.toObject(Destination.class);
                if (d != null && d.getId() == null) {
                    d.setId(doc.getId());
                }
                return d;
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi lấy destination: " + e.getMessage(), e);
        }
    }

    // ========== Trips ==========

    public List<Trip> getTripsByUser(String userId) {
        try {
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_TRIPS)
                    .whereEqualTo("userId", userId)
                    .get();
            QuerySnapshot snapshot = future.get();
            List<Trip> list = new ArrayList<>();
            for (DocumentSnapshot doc : snapshot.getDocuments()) {
                Trip t = doc.toObject(Trip.class);
                if (t != null && t.getId() == null) {
                    t.setId(doc.getId());
                }
                list.add(t);
            }
            return list;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi lấy trips: " + e.getMessage(), e);
        }
    }

    public String createTrip(Trip trip) {
        try {
            ApiFuture<DocumentReference> future = db.collection(COLLECTION_TRIPS).add(trip);
            return future.get().getId();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi tạo trip: " + e.getMessage(), e);
        }
    }

    public void updateTrip(String id, Trip trip) {
        try {
            db.collection(COLLECTION_TRIPS).document(id).set(trip).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi cập nhật trip: " + e.getMessage(), e);
        }
    }

    public void deleteTrip(String id) {
        try {
            db.collection(COLLECTION_TRIPS).document(id).delete().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi xóa trip: " + e.getMessage(), e);
        }
    }

    // ========== Bookings ==========

    public List<Booking> getBookingsByTrip(String tripId) {
        try {
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_BOOKINGS)
                    .whereEqualTo("tripId", tripId)
                    .get();
            QuerySnapshot snapshot = future.get();
            List<Booking> list = new ArrayList<>();
            for (DocumentSnapshot doc : snapshot.getDocuments()) {
                Booking b = doc.toObject(Booking.class);
                if (b != null && b.getId() == null) {
                    b.setId(doc.getId());
                }
                list.add(b);
            }
            return list;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi lấy bookings: " + e.getMessage(), e);
        }
    }

    public String createBooking(Booking booking) {
        try {
            ApiFuture<DocumentReference> future = db.collection(COLLECTION_BOOKINGS).add(booking);
            return future.get().getId();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi tạo booking: " + e.getMessage(), e);
        }
    }

    // ========== Users ==========

    public User getUserById(String id) {
        try {
            DocumentSnapshot doc = db.collection(COLLECTION_USERS).document(id).get().get();
            if (doc.exists()) {
                User u = doc.toObject(User.class);
                if (u != null && u.getId() == null) {
                    u.setId(doc.getId());
                }
                return u;
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Lỗi lấy user: " + e.getMessage(), e);
        }
    }
}
