package com.atakanunlu.STLbnb.service;

import com.atakanunlu.STLbnb.dto.BookingDto;
import com.atakanunlu.STLbnb.dto.BookingRequest;
import com.atakanunlu.STLbnb.dto.GuestDto;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
