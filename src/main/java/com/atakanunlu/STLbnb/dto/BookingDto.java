package com.atakanunlu.STLbnb.dto;

import com.atakanunlu.STLbnb.entity.Hotel;
import com.atakanunlu.STLbnb.entity.Room;
import com.atakanunlu.STLbnb.entity.User;
import com.atakanunlu.STLbnb.entity.enums.BookingStatus;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
@Data
public class BookingDto {

    private Long id;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
