package com.atakanunlu.STLbnb.dto;

import com.atakanunlu.STLbnb.entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDto {

    private Long Id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;

}
