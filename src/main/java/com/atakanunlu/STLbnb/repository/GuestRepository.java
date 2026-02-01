package com.atakanunlu.STLbnb.repository;

import com.atakanunlu.STLbnb.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
