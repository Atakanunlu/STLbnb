package com.atakanunlu.STLbnb.service;

import com.atakanunlu.STLbnb.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);

}
