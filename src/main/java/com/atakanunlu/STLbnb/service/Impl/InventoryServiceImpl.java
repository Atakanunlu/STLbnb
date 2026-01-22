package com.atakanunlu.STLbnb.service.Impl;

import com.atakanunlu.STLbnb.entity.Inventory;
import com.atakanunlu.STLbnb.entity.Room;
import com.atakanunlu.STLbnb.repository.InventoryRepository;
import com.atakanunlu.STLbnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public void initializeRoomForAYear(Room room) {

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);

        for (; !today.isAfter(endDate); today = today.plusDays(1)){

            Inventory inventory = Inventory
                    .builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .surgeFactor(BigDecimal.ONE)
                    .price(room.getBasePrice())
                    .build();

            inventoryRepository.save(inventory);
        }

    }

    @Override
    public void deleteFutureInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByDateAfterAndRoom(today,room);
    }
}
