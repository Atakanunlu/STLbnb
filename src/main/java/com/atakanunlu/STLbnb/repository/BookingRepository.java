package com.atakanunlu.STLbnb.repository;

import com.atakanunlu.STLbnb.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
