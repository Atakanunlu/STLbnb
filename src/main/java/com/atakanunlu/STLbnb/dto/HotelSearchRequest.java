package com.atakanunlu.STLbnb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {

    private String city;
    private LocalDate endDate;
    private LocalDate startDate;
    private Integer roomsCount;

    private Integer page=0;
    private Integer size=10;

}
