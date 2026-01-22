package com.atakanunlu.STLbnb.repository;

import com.atakanunlu.STLbnb.entity.Inventory;
import com.atakanunlu.STLbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);

}
