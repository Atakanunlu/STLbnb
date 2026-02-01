package com.atakanunlu.STLbnb.dto;

import com.atakanunlu.STLbnb.entity.User;
import com.atakanunlu.STLbnb.entity.enums.Gender;

import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
