package com.atakanunlu.STLbnb.service;

import com.atakanunlu.STLbnb.dto.HotelDto;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(HotelDto hotelDto, Long hotelId);

    void deleteHotelById(Long id);

    void activateHotel(Long hotelId);
}
