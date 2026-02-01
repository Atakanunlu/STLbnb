package com.atakanunlu.STLbnb.service;

import com.atakanunlu.STLbnb.dto.HotelDto;
import com.atakanunlu.STLbnb.dto.HotelInfoDto;
import com.atakanunlu.STLbnb.dto.HotelSearchRequest;
import com.atakanunlu.STLbnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
